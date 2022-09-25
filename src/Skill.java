package src;

public class Skill {
    int id;
    String name;
    int atk;

    public Skill(int id, String name, int atk) {
        this.id = id;
        this.name = name;
        this.atk = atk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
