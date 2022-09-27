package src.main;

public abstract class Interaction {
    Player player;
    NPC npc;  // onlu a place holder, not used in all interactions

    abstract void interrupt() throws GameException;

    Interaction(Player p) {
        player = p;
    }
}
