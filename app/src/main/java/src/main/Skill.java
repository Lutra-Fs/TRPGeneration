package src.main;

public class Skill {
    String name;
    int atk;

    public Skill(String name, int atk) {
        this.name = name;
        this.atk = atk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }
}
