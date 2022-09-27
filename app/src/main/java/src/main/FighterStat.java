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
    //Set
    public List<Skill> getSkills() {
        return skills;
    }
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
    public void setHP() throws GameException {
        //If level up, the HP set to MaxHP
        levelUp();
        this.HP = getMaxHP();
    }
    public void setMaxHP(int maxHP) {
        MaxHP = maxHP;
    }
    public void setMaxMP(int maxMP) {
        MaxMP = maxMP;
    }
    public void setMP() throws GameException {
        //If level up, the MP set to MaxMP
        levelUp();
        this.MP = getMaxMP();
    }
    public void setAtk(int atk) {this.atk = atk;}
    public void setDef(int def) {this.def = def;}
    public void setExp(int exp) {this.exp = exp;}

    //Get
    public int getHP() {
        return HP;
    }
    public int getMaxHP() {
        return MaxHP;
    }
    public int getMaxMP() {
        return MaxMP;
    }
    public int getMP() {
        return MP;
    }
    public int getFighterLevel() {
        return fighterLevel;
    }
    public void setFighterLevel(int fighterLevel) {this.fighterLevel = fighterLevel;}
    public int getAtk() {return atk;}
    public int getDef() {return def;}
    public int getExp() {return exp;}
    public Skill getSkill(int index){
        return skills.get(index);
    }
    public Skill getSkillAI(){
        //Default_baseAttack
        return getSkill(0);
    }
    
    //Other Function
    public void addHP(int HP)  {
        //HP cannot exceed maxHP
        if(HP+getHP()>MaxHP) {
            this.HP = HP+getHP();
        }
    }

    public void addMP(int MP)  {
         //MP cannot exceed maxMP
        if(MP+getMP()>MaxMP) {
            this.MP = MP+getMP();
        }
    }
    public Skill calcSkillATK(Skill s){
        //Calculate skill damage according to fighter level
        int damage = s.getAtk();
        damage += getFighterLevel()*10;
        s.setAtk(damage);
        return s;
    }

    public void levelUp() throws GameException {
        //if exp exceed each level exp, fighter level up
        if(getExp()<eachLevelExp){
            throw new GameException();
        }
    }

    public void beAttacked(Skill s) throws GameException {
        // if attack damage is larger than defence, fighter received damage
        if(calcSkillATK(s).getAtk()<getDef()){
            throw new GameException();
        }
    }

    public boolean isDead(){
        ///if HP is equal or lower than 0, fighter dead
        return getHP() <= 0;
    }

}
