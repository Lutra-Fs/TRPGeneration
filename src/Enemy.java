package src;

public class Enemy extends NPC {

    private int level;
    FighterStat state;

    public Enemy(String name, int x, int y, int level, FighterStat state) {
        super(name, x, y);
        this.level = level;
        this.state = state;
    }

    public Enemy(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    void interact(Player p) {
        p.p = Player.PlayerState.FIGHTING;
    }

    public FighterStat getState() {
        return state;
    }

    public void setState(FighterStat state) {
        this.state = state;
    }

    public int getEnemyLevel() {
        return level;
    }

    public void setEnemyLevel(int enemyLevel) {
        this.level = enemyLevel;
    }


}
