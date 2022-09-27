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
     * @param hp           fighter health
     * @param mp           fighter energy
     * @param atk          fighter attack
     * @param def          fighter defence
     * @param fighterLevel fighter level
     * @param exp          fighter experience
     * @param expPerLevel  max experience of each level
     * @param skills       fight skill
     */
    public FighterStat(int hp, int mp, int atk, int def, int fighterLevel, int exp, int expPerLevel, List<Skill> skills) {
        this.hp = hp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.fighterLevel = fighterLevel;
        this.exp = exp;
        this.expPerLevel = expPerLevel;
        this.skills = skills;
        this.maxMP = mp;
        this.maxHP = hp;
    }

    /**
     * @return List of skills
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * @throws GameException if level up is invalid
     */
    public void resetHP() {
        // If level up, the HP set to MaxHP
        this.hp = getMaxHP();
    }

    /**
     * @throws GameException if level up is invalid
     */
    public void resetMP() {
        // If level up, the MP set to MaxMP
        this.mp = getMaxMP();
    }

    void setHp(int hp) {
        if (hp > getMaxHP()) {
            this.hp = getMaxHP();
        }
        if (hp < 0) {
            this.hp = 0;
        }
        this.hp = hp;
    }

    void setMp(int mp) {
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
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * @return HP Health
     */
    public int getHp() {
        return hp;
    }

    /**
     * @return MP energy
     */
    public int getMp() {
        return mp;
    }

    /**
     * @return MaxHP
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * @return MaxMP
     */
    public int getMaxMP() {
        return maxMP;
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
    public int getDef() {
        return def;
    }

    /**
     * @return experience
     */
    public int getExp() {
        return exp;
    }

    /**
     * @return chosen skill
     */
    public Skill getSkill(int index) {
        return skills.get(index);
    }

    /**
     * @return skill with base attack
     */
    public Skill getSkillAI() {
        return getSkill(0);
    }

    int getExpPerLevel() {
        return expPerLevel;
    }

    /**
     * @param hp if current hp plus adding hp is not exceed maxHP, Current hp add hp
     */
    public void addHP(int hp) {
        // hp cannot exceed maxHP
        setHp(getHp() + hp);
    }

    /**
     * @param mp if current mp plus adding mp is not exceed maxHP, Current mp add mp
     */
    public void addMP(int mp) {
        // mp cannot exceed maxMP
        setMp(getMp() + mp);
    }

    /**
     * @param s skill chose by user
     * @return skill with new damage value
     */
    public Skill calcSkillATK(Skill s) {
        // Calculate skill damage according to fighter level
        int damage = s.getAtk();
        damage += getFighterLevel() * 10;
        s.setAtk(damage);
        return s;
    }


    /**
     * @throws GameException if exp not reach each level exp, throw exception else level add 1
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
     * @throws GameException if attack damage is less than defence, throw exception else loss HP
     */
    public void beAttacked(Skill s) throws GameException {
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
     */
    public boolean isDead() {
        // /if HP is equal or lower than 0, fighter dead
        return getHp() <= 0;
    }

    public Skill getSkillByName(String name) throws GameException {
        for (Skill s : skills) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        throw new GameException();
    }
}
