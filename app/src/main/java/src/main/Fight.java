package src.main;

import java.util.List;

public class Fight extends Interaction {

    Enemy npc;
    FighterStat playerStat;
    FighterStat enemyStat;
    boolean isPlayerTurn;

    /**
     * @param player current player
     * @param npc    the npc that player is interacting with
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
    public List<Skill> getPlayerSkills() {
        return playerStat.getSkills();
    }

    /**
     * @return player backpack
     */
    public List<Backpack.Thing> getPlayerBackpack() {
        return player.getThings();
    }

    /**
     * @param name thing name
     * @throws GameException if Thing is not in backpack, throw exception
     */
    public void useThings(String name) throws GameException {
        Backpack.Thing t;
        // Check if Thing is valid to use
        if (name.matches("\\d+")) {
            int index = Integer.parseInt(name);
            if (index < getPlayerSkills().size()) {
                // find the skill via index
                t = getPlayerBackpack().get(index);
            } else {
                t = player.b.getThing(name);
            }
        } else {
            t = player.b.getThing(name);
        }
        if (t == null) {
            throw new GameException("No such thing");
        } else {
            player.getBackpack().use(t, playerStat);
        }
        isPlayerTurn = !isPlayerTurn;
    }

    /**
     * @throws GameException if skill is not valid, throw exception
     */
    public void useSkill() throws GameException {
        // Check if Skill is valid to use
        FighterStat s = isPlayerTurn ? playerStat : enemyStat;
        Skill sAi = s.calcSkillATK(s.getSkillAI());
        FighterStat e = isPlayerTurn ? enemyStat : playerStat;
        e.beAttacked(sAi);
        if (isEnd()) {
            interrupt();
        }
        isPlayerTurn = !isPlayerTurn;
    }

    /**
     * @param name skill name
     * @throws GameException if skill is not in skill list, throw exception
     */
    public void useSkill(String name) throws GameException {
        // Check if Skill is valid to use
        Skill s;
        if (name.matches("\\d+")) {
            int index = Integer.parseInt(name);
            if (index < getPlayerSkills().size()) {
                // find the skill via index
                s = getPlayerSkills().get(index);
            } else {
                s = playerStat.getSkillByName(name);
            }
        } else {
            // find the skill via name
            s = playerStat.getSkillByName(name);
        }
        if (s == null) {
            throw new GameException();
        }
        FighterStat s1 = isPlayerTurn ? playerStat : enemyStat;
        FighterStat e = isPlayerTurn ? enemyStat : playerStat;
        // check if player has enough MP
        if (s1.getMp() < s.getMp()) {
            throw new GameException();
        }
        Skill sParse = s1.calcSkillATK(s);
        s1.setMp(s1.getMp() - sParse.getMp());
        e.beAttacked(sParse);
        // check if the fight can end
        if (isEnd()) {
            interrupt();
        }
        isPlayerTurn = !isPlayerTurn;
    }


    /**
     * @return play or enemy dead statement
     */
    public boolean isEnd() {
        return playerStat.isDead() || enemyStat.isDead();
    }

    /**
     * @throws GameException if player is dead, throw play dead exception
     */
    @Override
    void interrupt() throws GameException {
        player.p = Player.PlayerState.NORMAL;
        if (playerStat.isDead()) {
            throw new GameException.PlayerDeadException();
        } else {
            //increase the exp based on level different between fighters' level
            int levelDifference = enemyStat.getFighterLevel() - playerStat.getFighterLevel();
            double e = Math.E;
            int exp = (int) Math.pow(e, levelDifference);
            playerStat.setExp(playerStat.getExp() + exp);
            //check if player level up
            int curLevel = playerStat.getExp() / playerStat.getExpPerLevel();
            if (curLevel > playerStat.getFighterLevel()) {
                playerStat.levelUp();
            }
            //Add money
            player.money += 5 * levelDifference;
            // reset player state
            player.fightStat = playerStat;
        }
    }
}
