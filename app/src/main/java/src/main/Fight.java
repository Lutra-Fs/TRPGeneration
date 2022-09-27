package src.main;
import java.util.List;

public class Fight extends Interaction {

    /**
     * @param npc initiate npc
     * @param playerStat player statement
     * @param enemyStat enemy statement
     */
    Enemy npc;
    FighterStat playerStat;
    FighterStat enemyStat;
    boolean isPlayerTurn;

    /**
     * @param player
     * @param npc
     */
    public Fight(Player player, Enemy npc) {
        super(player);
        this.npc = npc;
        this.playerStat = player.fightStat;
        this.enemyStat = npc.state;
    }

    /**
     * @return player skills
     */
    public List<Skill> getPlayerSkills(){
        return playerStat.getSkills();
    }

    /**
     * @return player backpack
     */
    public List<Backpack.Thing> getPlayerBackpack() {return player.getBackpack();}

    /**
     * @param name thing name
     * @throws GameException if Thing is not in backpack, throw exception
     */
    public void useThings(String name) throws GameException{
        // Check if Thing is valid to use
        if (name.matches("\\d+")){
            int index = Integer.parseInt(name);
            if(index > getPlayerBackpack().size()){
                throw new GameException();
            }
        }
    }

    /**
     * @throws GameException if skill is not valid, throw exception
     */
    public void useSkill() throws GameException{
        // Check if Skill is valid to use
        FighterStat s = isPlayerTurn? playerStat:enemyStat;
        Skill sAi = s.calcSkillATK(s.getSkillAI());
        FighterStat e = isPlayerTurn? enemyStat:playerStat;
        e.beAttacked(sAi);
    }

    /**
     * @param name skill name
     * @throws GameException if skill is not in skill list, throw exception
     */
    public void useSkill(String name) throws GameException{
        // Check if Skill is valid to use
        if (name.matches("\\d+")){
            int index = Integer.parseInt(name);
            if(index > getPlayerSkills().size()){
                throw new GameException();
            }
        }
    }

    /**
     * @return play or enemy dead statement
     */
    public boolean isEnd(){
        return playerStat.isDead() || enemyStat.isDead();
    }

    /**
     * @throws GameException if player is dead, throw play dead exception
     */
    @Override
    void interrupt() throws GameException {
        player.p = Player.PlayerState.NORMAL;
        if(playerStat.isDead()){
            throw new GameException.PlayerDeadException();
        }else{
            //increase the exp based on level different between fighters' level
            int levelDifference = enemyStat.getFighterLevel() - playerStat.getFighterLevel();
            double e = Math.E;
            int exp = (int) Math.pow(e, levelDifference);
            playerStat.setExp(exp);
            playerStat.levelUp();
            //Add money
            player.money += 5*levelDifference;
            player.fightStat = playerStat;
        }
    }
}
