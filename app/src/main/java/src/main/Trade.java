package src.main;

/**
 * initialize and manage trade action in the game
 *
 * @author Bo ZHANG
 */
public class Trade extends Interaction {
    Trader npc;

    /**
     * constructor for trade
     *
     * @param p   player
     * @param npc npc
     * @author Bo ZHANG
     */
    Trade(Player p, Trader npc) {
        super(p);
        this.npc = npc;
    }

    /**
     * trade with npc, sell a thing from npc to player
     *
     * @param s thing to sell thing's name || index
     * @throws GameException for calling method
     * @author Bo ZHANG
     */
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

    void trade(Backpack.Thing t) throws GameException {
        npc.sell(t);
        player.buy(t);
        if (player.money < 0) {
            interrupt();
        }
        if (npc.getRemainingThingsSize() == 0) {
            interrupt();
        }
    }

    /**
     * @throws GameException when player's money is less than 0 || npc's remaining things is 0 || manually interrupt
     */
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
