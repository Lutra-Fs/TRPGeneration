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

    /**
     *
     * @param HP fighter health
     * @param MP fighter energy
     * @param atk fighter attack
     * @param def fighter defence level
     * @param fighterLevel fighter level
     * @param exp fighter experience
     * @param eachLevelExp max experience of each level
     * @param skills fight skill
     */
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

    /**
     * @return  List of skills
     */
    public List<Skill> getSkills() {return skills;}

    /**
     * @throws GameException if level up is invalid
     */
    public void setHP() throws GameException {
        //If level up, the HP set to MaxHP
        levelUp();
        this.HP = getMaxHP();
    }

    /**
     * @throws GameException if level up is invalid
     */
    public void setMP() throws GameException {
        //If level up, the MP set to MaxMP
        levelUp();
        this.MP = getMaxMP();
    }

    /**
     * @param exp fighter experience
     */
    public void setExp(int exp) {this.exp = exp;}

    /**
     * @return HP
     */
    public int getHP() {
        return HP;
    }

    /**
     * @return MaxHP
     */
    public int getMaxHP() {
        return MaxHP;
    }

    /**
     * @return MaxMP
     */
    public int getMaxMP() {
        return MaxMP;
    }

    /**
     * @return MP
     */
    public int getMP() {
        return MP;
    }

    /**
     * @return fighterLevel
     */
    public int getFighterLevel() {
        return fighterLevel;
    }

    /**
     * @return defence
     */
    public int getDef() {return def;}

    /**
     * @return experience
     */
    public int getExp() {return exp;}

    /**
     * @return chosen skill
     */
    public Skill getSkill(int index){
        return skills.get(index);
    }

    /**
     * @return skill with base attack
     */
    public Skill getSkillAI(){
        return getSkill(0);
    }

    /**
     * @param HP if current HP plus adding HP is not exceed maxHP, Current HP add HP
     */
    public void addHP(int HP)  {
        //HP cannot exceed maxHP
        if(HP+getHP()<MaxHP) {
            this.HP = HP+getHP();
        }
    }

    /**
     * @param MP if current MP plus adding MP is not exceed maxHP, Current MP add MP
     */
    public void addMP(int MP)  {
         //MP cannot exceed maxMP
        if(MP+getMP()<MaxMP) {
            this.MP = MP+getMP();
        }
    }

    /**
     * @param s skill chose by user
     * @return skill with new damage value
     */
    public Skill calcSkillATK(Skill s){
        //Calculate skill damage according to fighter level
        int damage = s.getAtk();
        damage += getFighterLevel()*10;
        s.setAtk(damage);
        return s;
    }

    /**
     * @throws GameException if exp not reach each level exp, throw exception
     */
    public void levelUp() throws GameException {
        if(getExp()<eachLevelExp){
            throw new GameException();
        }
    }

    /**
     * @param s used Skill
     * @throws GameException if attack damage is less than defence, throw exception
     */
    public void beAttacked(Skill s) throws GameException {
        // if attack damage is larger than defence, fighter received damage
        if(calcSkillATK(s).getAtk()<getDef()){
            throw new GameException();
        }
    }

    /**
     * @return if fighter is dead or not
     */
    public boolean isDead(){
        ///if HP is equal or lower than 0, fighter dead
        return getHP() <= 0;
    }

}
