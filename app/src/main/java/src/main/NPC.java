package src.main;

public abstract class NPC {
    Location loc;
    String name;
    void interact(Player p){
        p.p = Player.PlayerState.INTERACTING;
    }
}
