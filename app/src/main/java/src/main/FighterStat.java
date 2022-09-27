package src.main;
import java.util.List;

public class FighterStat {
    int HP;
    int MP;
    int atk;
    int def;
    int fighterLevel;
    int exp;
    int MaxHP;
    int MaxMP;
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
        this.MaxMP = MP;
        this.MaxHP = HP;
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

    public void setHP() throws GameException {
        levelUp();
        this.HP = getMaxHP();
    }

    public void addHP(int HP)  {
        if(HP+getHP()>MaxHP) {
           this.HP = HP+getHP();
        }
    }

    public void addMP(int MP)  {
        if(MP+getMP()>MaxMP) {
            this.MP = MP+getMP();
        }
    }

    public int getMaxHP() {
        return MaxHP;
    }

    public void setMaxHP(int maxHP) {
        MaxHP = maxHP;
    }

    public int getMaxMP() {
        return MaxMP;
    }

    public void setMaxMP(int maxMP) {
        MaxMP = maxMP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP() throws GameException {
        levelUp();
        this.MP = getMaxMP();
    }
    public int getFighterLevel() {
        return fighterLevel;
    }
    public void setFighterLevel(int fighterLevel) {
        this.fighterLevel = fighterLevel;
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
