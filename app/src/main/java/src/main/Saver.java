package src.main;

/**
 * saving status of the game
 *
 * @author Bo ZHANG
 */
public class Saver extends NPC {
    /**
     * constructor for NPC
     *
     * @param name name of NPC
     * @param x    x coordinate of NPC
     * @param y    y coordinate of NPC
     * @author Bo ZHANG
     */
    protected Saver(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    void interact(Player p) {
        p.p = Player.PlayerState.SAVING;
    }
}
