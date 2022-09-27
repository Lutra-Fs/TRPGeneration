package src.main;

public class Skill {
    String name;
    int atk;
    int mp;


    /**
     * @param name Skill name
     * @param atk  skill attack damage
     * @param mp   skill attack cost energy
     */
    public Skill(String name, int atk, int mp) {
        this.name = name;
        this.atk = atk;
        this.mp = mp;
    }

    /**
     * @return skill name
     */
    public String getName() {
        return name;
    }

    /**
     * @return skill damage
     */
    public int getAtk() {
        return atk;
    }

    /**
     * @param atk set Skill attack with new damage
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * @return MP
     */
    public int getMp() {
        return mp;
    }

}
