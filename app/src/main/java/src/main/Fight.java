package src.main;
import java.util.List;

public class Fight extends Interaction {

    Enemy npc;
    Player player;
    FighterStat playerStat;
    FighterStat enemyStat;

    boolean isPlayerTurn;

    public Fight(Player player, Enemy npc) {
        this.player = player;
        this.npc = npc;
        this.playerStat = player.fightStat;
        this.enemyStat = npc.state;
    }
    public List<Skill> getPlayerSkills(){
        return playerStat.getSkills();
    }
    public List<Backpack.Thing> getPlayerBackpack() {
        return null;
    }

    public void useThings(String name) throws GameException{
        if (name.matches("\\d+")){
            int index = Integer.parseInt(name);
            if(index > getPlayerBackpack().size()){
                throw new GameException();
            }
        }
    }
    public void useSkill() throws GameException{
        FighterStat s = isPlayerTurn? playerStat:enemyStat;
        Skill sAi = s.calcSkillATK(s.getSkillAI());
        FighterStat e = isPlayerTurn? enemyStat:playerStat;
        e.beAttacked(sAi);
    }

    public void useSkill(String name) throws GameException{
        if (name.matches("\\d+")){
            int index = Integer.parseInt(name);
            if(index > getPlayerSkills().size()){
                throw new GameException();
            }
        }
    }

    public boolean isEnd(){
        return playerStat.isDead() || enemyStat.isDead();
    }

    @Override
    void interrupt() throws GameException {
        player.p = Player.PlayerState.NORMAL;
        if(playerStat.isDead()){
            throw new GameException.PlayerDeadException();
        }else{
            int levelDifference = enemyStat.getFighterLevel() - playerStat.getFighterLevel();
            double e = Math.E;
            int exp = (int) Math.pow(e, levelDifference);
            playerStat.setExp(exp);
            playerStat.levelUp();
            player.money += 5*levelDifference;
            player.fightStat = playerStat;
        }
    }
}
