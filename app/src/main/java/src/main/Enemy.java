package src.main;

public class Enemy extends NPC {

    private int level;
    FighterStat state;

    public Enemy(String name, int x, int y, int level, FighterStat state) {
        super(name, x, y);
        this.level = level;
        this.state = state;
    }

    @Override
    void interact(Player p) {
        p.p = Player.PlayerState.FIGHTING;
    }

    public FighterStat getState() {
        return state;
    }

}
