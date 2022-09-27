package src.main;

public class Skill {
    String name;
    int atk;
    int MP;


    /**
     * @param name Skill name
     * @param atk skill attack damage
     * @param MP skill attack cost energy
     */
    public Skill(String name, int atk, int MP) {
        this.name = name;
        this.atk = atk;
        this.MP = MP;
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
    public int getMP() {return MP;}
    
}
