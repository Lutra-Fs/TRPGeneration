package src.main;
import java.util.List;

public class FighterStat {
    int HP;
    int MP;
    int atk;
    int def;
    int fighterLevel;
    int exp;
    final int eachLevelExp;
    List<Skill> skills;

    public FighterStat(int HP, int MP, int atk, int def, int fighterLevel, int exp, int eachLevelExp, List<Skill> skills) {
        this.HP = HP;
        this.MP = MP;
        this.atk = atk;
        this.def = def;
        this.fighterLevel = fighterLevel;
        this.exp = exp;
        this.eachLevelExp = eachLevelExp;
        this.skills = skills;
    }
    public List<Skill> getSkills() {
        return skills;
    }
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getFighterLevel() {
        return fighterLevel;
    }

    public void setFighterLevel(int fighterLevel) {
        this.fighterLevel = fighterLevel;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Skill getSkill(int index){
        return skills.get(index);
    }

    public Skill getSkillAI(){
        //Default_baseAttack
        return getSkill(0);
    }
    public Skill calcSkillATK(Skill s){
        int damage = s.getAtk();
        damage += getFighterLevel()*10;
        s.setAtk(damage);
        return s;
    }

    public void levelUp() throws GameException {
        if(getExp()<eachLevelExp){
            throw new GameException();
        }
    }

    public void beAttacked(Skill s) throws GameException {
        if(calcSkillATK(s).getAtk()<getDef()){
            throw new GameException();
        }
    }

    public boolean isDead(){
        return getHP() <= 0;
    }

}
