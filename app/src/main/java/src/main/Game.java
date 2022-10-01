package src.main;

public class Game {
    static Interaction interaction;
    String gamePath;
    Player player;
    Level level;

    Game(Player p, Level l) {
        player = p;
        level = l;
    }

    synchronized Interaction getInteraction() {
        return interaction;
    }

    static synchronized void setInteraction(Interaction i) {
        interaction = i;
    }
}
