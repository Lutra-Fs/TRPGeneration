package src.main;

public abstract class NPC {
    Location loc;
    final String name;

    /**
     * constructor for NPC
     *
     * @param name name of NPC
     * @param x    x coordinate of NPC
     * @param y    y coordinate of NPC
     */
    protected NPC(String name, int x, int y) {
        this.name = name;
        loc = new Location(x, y);
    }

    /**
     * let the player interact with the NPC.
     * The default implementation does nothing.
     *
     * @param p player
     */
    void interact(Player p) {
        p.p = Player.PlayerState.INTERACTING;
    }

    /**
     * fallback method, calling when there is no Interaction.
     *
     * @param p player
     */
    void interrupt(Player p) {
        p.p = Player.PlayerState.NORMAL;
    }

}
