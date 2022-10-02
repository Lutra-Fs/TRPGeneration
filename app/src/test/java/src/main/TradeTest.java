package src.main;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeTest {

    List<Player> playerList = new ArrayList<>();
    Player player1;
    Player player2;
    Player player3;

    List<Trader> traderList = new ArrayList<>();
    Trader trader1;
    Trader trader2;
    Trader trader3;

    List<Trade> trade = new ArrayList<>();
    Trade trade1 = new Trade(player1, trader1);
    Trade trade2 = new Trade(player2, trader2);
    Trade trade3 = new Trade(player3, trader3);

    private TradeTest() {
        trade.add(trade1);
        trade.add(trade2);
        trade.add(trade3);
    }

    @Test
    void testSell() throws GameException {
        trader1.backpack.add(new Backpack.Thing("thing", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing2", 15, 12));
        trader1.backpack.add(new Backpack.Thing("thing3", 20, 14));
        trader1.backpack.add(new Backpack.Thing("thing4", 25, 16));
        trader1.backpack.add(new Backpack.Thing("thing5", 30, 18));

        trade1.npc.sell(trader1.sell("thing"));
        assertEquals(10, trader1.backpack.things , "Incorrect money");

        assertEquals("t", trader1, "Incorrect sell");
    }

    @Test
    void testBuy() throws GameException {
        trader1.backpack.add(new Backpack.Thing("thing", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing2", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing3", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing4", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing5", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing6", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing7", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing8", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing9", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing10", 10, 10));
        trader1.backpack.add(new Backpack.Thing("thing11", 10, 10));

    }
}
