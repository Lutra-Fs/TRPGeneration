package src.main;

import java.util.List;

public class FighterStat {
    int hp;
    int mp;
    int atk;
    int def;
    int fighterLevel;
    int exp;
    int maxHP;
    int maxMP;
    final int expPerLevel;
    List<Skill> skills;

    /**
     * Constructor for FighterStat
     *
     * @param hp          fighter health
     * @param mp          fighter energy
     * @param atk         fighter attack
     * @param def         fighter defence
     * @param expPerLevel max experience of each level
     * @param skills      fight skill
     * @author Juhao Tao
     * @author Bo ZHANG
     */
    public FighterStat(int hp, int mp, int atk, int def, int expPerLevel, List<Skill> skills) {
        this.hp = hp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.fighterLevel = 1;
        this.expPerLevel = expPerLevel;
        this.skills = skills;
        this.maxMP = mp;
        this.maxHP = hp;
    }

    /**
     * get player's available skills
     *
     * @return List of skills
     * @author Bo ZHANG
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * set HP to maxHP
     *
     * @author Bo ZHANG
     * @author Juhao Tao
     */
    public void resetHP() {
        // If level up, the HP set to MaxHP
        this.hp = getMaxHP();
    }

    /**
     * set MP to maxMP
     *
     * @author Bo ZHANG
     * @author Juhao Tao
     */
    public void resetMP() {
        // If level up, the MP set to MaxMP
        this.mp = getMaxMP();
    }

    /**
     * Set hp to a new value
     * This method make sure hp is not larger than maxHP or smaller than 0
     *
     * @param hp new HP
     * @author Bo ZHANG
     * @author Juhao Tao
     */
    void setHp(int hp) {
        //HP cannot exceed maxHP
        if (hp > getMaxHP()) {
            this.hp = getMaxHP();
        }
        if (hp < 0) {
            this.hp = 0;
        }
        this.hp = hp;
    }

    /**
     * Set mp to a new value
     * This method make sure mp is not larger than maxMP or smaller than 0
     *
     * @param mp new MP
     * @author Bo ZHANG
     * @author Juhao Tao
     */
    void setMp(int mp) {
        //MP cannot exceed maxMP
        if (mp > getMaxMP()) {
            this.mp = getMaxMP();
        }
        if (mp < 0) {
            this.mp = 0;
        }
        this.mp = mp;
    }

    /**
     * @param exp fighter experience
     * @author Bo ZHANG
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * @return HP Health
     * @author Juhao Tao
     */
    public int getHp() {
        return hp;
    }

    /**
     * @return MP energy
     * @author Juhao Tao
     */
    public int getMp() {
        return mp;
    }

    /**
     * @return MaxHP
     * @author Juhao Tao
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * @return MaxMP
     * @author Juhao Tao
     */
    public int getMaxMP() {
        return maxMP;
    }

    /**
     * @return fighterLevel
     * @author Juhao Tao
     */
    public int getFighterLevel() {
        return fighterLevel;
    }

    /**
     * @return defence
     * @author Bo ZHANG
     */
    public int getDef() {
        return def;
    }

    /**
     * @return experience
     * @author Bo ZHANG
     */
    public int getExp() {
        return exp;
    }

    /**
     * @return chosen skill
     * @author Juhao Tao
     */
    public Skill getSkill(int index) {
        return skills.get(index);
    }

    /**
     * TODO: return other skills rather than the base attack
     *
     * @return skill with base attack
     * @author Juhao Tao
     */
    public Skill getSkillAI() {
        return getSkill(0);
    }

    /**
     * @return experience of each level
     * @author Bo ZHANG
     */
    int getExpPerLevel() {
        return expPerLevel;
    }

    /**
     * add HP to current HP
     *
     * @param hp HP to add
     * @author Juhao Tao
     * @author Bo ZHANG
     */
    public void addHP(int hp) {
        setHp(getHp() + hp);
    }

    /**
     * add MP to current MP
     *
     * @param mp MP to add
     * @author Juhao Tao
     * @author Bo ZHANG
     */
    public void addMP(int mp) {
        // mp cannot exceed maxMP
        setMp(getMp() + mp);
    }

    /**
     * THis method is to calculate the skill attack according to fighter level
     *
     * @param s skill chose by user
     * @return skill with new damage value
     * @author Juhao Tao
     */
    public Skill calcSkillATK(Skill s) {
        int damage = s.getAtk();
        damage += getFighterLevel() * 10;
        s.setAtk(damage);
        return s;
    }


    /**
     * This method is to determine if fighter is valid to level up
     * Increase attribute when fighter level up
     *
     * @throws GameException if exp not reach each level exp, throw exception else level add 1
     * @author Bo ZHANG
     * @author Juhao Tao
     */
    public void levelUp() throws GameException {
        if (getExpPerLevel() * (getFighterLevel() + 1) > getExp()) {
            throw new GameException("Not enough exp");
        }
        fighterLevel++;
        // attribute increase
        maxHP += 20;
        maxMP += 15;
        atk += 10;
        def += 8;
        // reset HP and MP
        resetHP();
        resetMP();
    }

    /**
     * This method is used to calculate the actual damage of the attack considering the defence of the defender.
     * The attack order is that attacker find skills, then calculate the damage of the skill,
     * then defender calculate the actual damage.
     *
     * @param s the skill which has calculated damage by the attacker's atk
     * @author Juhao Tao
     * @author Bo ZHANG
     */
    public void beAttacked(Skill s) {
        // if attack damage is larger than defence, fighter received damage
        s.setAtk(s.getAtk() - (int) (getDef() * 0.25)); // consider defence
        if (s.getAtk() < getDef()) {
            this.hp = getHp() - (int) (s.getAtk() * 0.1); // if the damage is less than defence, penalty will be applied
        } else {
            this.hp = getHp() - s.getAtk();
        }
    }

    /**
     * @return if fighter is dead or not
     * @author Juhao Tao
     */
    public boolean isDead() {
        // /if HP is equal or lower than 0, fighter dead
        return getHp() <= 0;
    }

    /**
     * @param name SKill name
     * @return Skill
     * @throws GameException if skill invalid, throw exception
     * @author Bo ZHANG
     */
    public Skill getSkillByName(String name) throws GameException {
        for (Skill s : skills) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        throw new GameException();
    }
}
