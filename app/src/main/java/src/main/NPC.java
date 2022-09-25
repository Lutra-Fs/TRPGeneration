package src.main;

public abstract class NPC {
    Location loc;
    final String name;

    public NPC(String name, int x, int y) {
        this.name = name;
        loc = new Location(x, y);
    }

    void interact(Player p){
        p.p = Player.PlayerState.INTERACTING;
    }
    void interrupt(Player p){
        p.p = Player.PlayerState.NORMAL;
    }
}
