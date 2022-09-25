package src.main;

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
