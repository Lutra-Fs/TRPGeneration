package src;
import java.util.List;

public class Fight {

    Enemy npc;
    Enemy player;
    FighterStat playerStat;
    FighterStat enemyStat;
    List<Skill> skills;
    List<Thing> things;

    boolean IsPlayerTurn;

    public Fight(Enemy player, Enemy npc, boolean IsPlayerTurn) {
        this.player = player;
        this.npc = npc;
        this.IsPlayerTurn = IsPlayerTurn;
    }

    public void getPlayerSkills(List<Skill> skill_list) {
        this.skills = skill_list;
    }

    public void getThing(List<Thing> things) {
        this.things = things;
    }

    public List<Thing> getPlayerBackpack() {
        return null;
    }

    public void useThings(Thing t) throws GameException {

    }
    public void useThings(int index) throws GameException{
        if (things.get(index) == null){
            throw new GameException("invalid Thing");
        }
    }
    public void useThings(String name) throws GameException{
        boolean check = false;
        for (Thing thing : things) {
            if (thing.getName().equals(name)) {
                check = true;
                break;
            }
        }
        if(!check){
            throw new GameException("invalid Thing");
        }
    }

    public void useSkill(Skill s) throws GameException{

    }

    public void useSkill(int index) throws GameException{
        boolean check = false;
        for (Skill skill : skills) {
            if (skill.getId() == index) {
                check = true;
                break;
            }
        }
        if(!check){
            throw new GameException("invalid Skill");
        }
    }
    public void useSkill(String name) throws GameException{
        boolean check = false;
        for (Skill skill : skills) {
            if (skill.getName().equals(name)) {
                check = true;
                break;
            }
        }
        if(!check){
            throw new GameException("invalid Skill");
        }
    }
    public void makeAttack(Skill s){

    }

    public boolean isEnd(){
        return playerStat.isDead() || enemyStat.isDead();
    }
}
