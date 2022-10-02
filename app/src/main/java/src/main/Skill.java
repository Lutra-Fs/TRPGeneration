package src.main;

/**
 * skill of the player and the NPC
 *
 * @author Bo ZHANG
 */
public class Skill {
    String name;
    int atk;
    int mp;


    /**
     * constructor for Skill
     *
     * @param name Skill name
     * @param atk  skill attack damage
     * @param mp   skill attack cost energy
     * @author Juhao Tao
     */
    public Skill(String name, int atk, int mp) {
        this.name = name;
        this.atk = atk;
        this.mp = mp;
    }

    /**
     * get the name of skill
     *
     * @return skill name
     * @author Juhao Tao
     */
    public String getName() {
        return name;
    }

    /**
     * get the atk of skill
     *
     * @return skill damage
     * @author Juhao Tao
     */
    public int getAtk() {
        return atk;
    }

    /**
     * set the atk of skill
     *
     * @param atk set Skill attack with new damage
     * @author Juhao Tao
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * get MP for skill
     *
     * @return MP
     * @author Juhao Tao
     */
    public int getMp() {
        return mp;
    }

}
