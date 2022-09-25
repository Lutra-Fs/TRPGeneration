package src.main;

public abstract class Interaction {
    Player player;
    NPC npc;
    abstract void interact();
    abstract void interrupt();
}
