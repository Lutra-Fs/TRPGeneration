package src.main;

public class Enemy extends NPC {

    private int level;
    FighterStat state;

    /**
     * @param name NPC name
     * @param x location x-coordinate
     * @param y location y-coordinate
     * @param level NPC level
     * @param state NPC statement
     */
    public Enemy(String name, int x, int y, int level, FighterStat state) {
        super(name, x, y);
        this.level = level;
        this.state = state;
    }

    /**
     * @param p player state
     */
    @Override
    void interact(Player p) {
        //Set player state to fighting
        p.p = Player.PlayerState.FIGHTING;
    }

}
