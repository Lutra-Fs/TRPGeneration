package src.main;

public class Skill {
    String name;
    int atk;

    /**
     * @param name Skill name
     * @param atk skill attack damage
     */
    public Skill(String name, int atk) {
        this.name = name;
        this.atk = atk;
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
}
