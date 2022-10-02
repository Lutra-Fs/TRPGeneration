package src.main;

/**
 * handle the exception of the game
 *
 * @author Bo ZHANG
 */
public class GameException extends Exception {
    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameException(Throwable cause) {
        super(cause);
    }

    public GameException() {
        super();
    }

    static class PlayerDeadException extends GameException {
        public PlayerDeadException() {
            super();
        }
    }
}
