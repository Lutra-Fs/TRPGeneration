package src.main;

public abstract class Interaction {
    Player player;
    NPC npc;

    abstract void interrupt() throws GameException;

    Interaction(Player p) {
        player = p;
    }
}
