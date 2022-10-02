package src.main;

/**
 * provide a game state of the game
 *
 * @author Bo ZHANG
 */
public class Game {
    static Interaction interaction;
    String gamePath;
    Player player;
    Level level;

    /**
     * Constructor for Game
     *
     * @param p the player
     * @param l the level
     * @author Bo ZHANG
     */
    Game(Player p, Level l) {
        player = p;
        level = l;
    }

    /**
     * get the interaction
     *
     * @return interaction
     * @author Bo ZHANG
     */
    synchronized Interaction getInteraction() {
        return interaction;
    }

    /**
     * set the interaction
     *
     * @param i
     * @author Bo ZHANG
     */
    static synchronized void setInteraction(Interaction i) {
        interaction = i;
    }
}
