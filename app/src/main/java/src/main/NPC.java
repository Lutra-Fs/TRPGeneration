package src.main;

/**
 * NPC class is used to initialize the NPC
 *
 * @author Bo ZHANG
 */

public abstract class NPC {
    final String name;
    Location loc;

    /**
     * constructor for NPC
     *
     * @param name name of NPC
     * @param x    x coordinate of NPC
     * @param y    y coordinate of NPC
     * @author Bo ZHANG
     */
    protected NPC(String name, int x, int y) {
        this.name = name;
        loc = new Location(x, y);
    }

    protected NPC(String name) {
        Location.setMax(1, 1);
        this.name = name;
        loc = new Location(0, 0);
    }

    /**
     * let the player interact with the NPC.
     * The default implementation does nothing.
     *
     * @param p player
     * @author Bo ZHANG
     */
    void interact(Player p) {
        p.p = Player.PlayerState.INTERACTING;
    }

    /**
     * fallback method, calling when there is no Interaction.
     *
     * @param p player
     * @author Bo ZHANG
     */
    void interrupt(Player p) {
        p.p = Player.PlayerState.NORMAL;
    }

    public String getName() {
        return name;
    }
}
