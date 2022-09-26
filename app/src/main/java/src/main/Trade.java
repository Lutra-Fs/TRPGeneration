package src.main;

public class Trade extends Interaction {
    Trader npc;

    void trade(String s) throws GameException {
        Backpack.Thing t = npc.sell(s);
        player.buy(t);
        if (player.money < 0) {
            interrupt();
        }
        if (npc.getRemainingThingsSize() == 0) {
            interrupt();
        }
    }

    Trade(Player p, Trader npc) {
        super(p);
        this.npc = npc;
    }

    @Override
    void interrupt() throws GameException {
        player.p = Player.PlayerState.NORMAL;
        
        if (player.money < 0) {
            throw new GameException("You don't have enough money");
        } else if (npc.getRemainingThingsSize() == 0) {
            throw new GameException("The trader has no more things to sell");
        } else {
            throw new GameException("You have interrupted the trade");
        }
    }


}
