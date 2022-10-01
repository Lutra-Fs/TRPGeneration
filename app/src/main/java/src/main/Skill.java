package src.main;

public class Skill {
    String name;
    int atk;
    int mp;


    /**
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
     * @return skill name
     * @author Juhao Tao
     */
    public String getName() {
        return name;
    }

    /**
     * @return skill damage
     * @author Juhao Tao
     */
    public int getAtk() {
        return atk;
    }

    /**
     * @param atk set Skill attack with new damage
     * @author Juhao Tao
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * @return MP
     * @author Juhao Tao
     */
    public int getMp() {
        return mp;
    }

}
