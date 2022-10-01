package src.main;

public abstract class Interaction {
    Player player;

    /**
     * Constructor for Interaction
     *
     * @param p the player
     * @author Bo ZHANG
     */
    Interaction(Player p) {
        player = p;
    }

    /**
     * interrupt the interaction
     *
     * @throws GameException for the interaction to handle
     * @author Bo ZHANG
     */
    abstract void interrupt() throws GameException;
}
