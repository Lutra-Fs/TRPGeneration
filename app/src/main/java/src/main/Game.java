package src.main;

public class Game {
    String gamePath;
    Player player;
    Level level;
    static Interaction interaction;

    Game(Player p, Level l) {
        player = p;
        level = l;
    }

    static synchronized void setInteraction(Interaction i) {
        interaction = i;
    }

    synchronized Interaction getInteraction() {
        return interaction;
    }
}
