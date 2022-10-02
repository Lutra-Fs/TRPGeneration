package src.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TradeTest {

    Player player1=new Player();
    Player player2=new Player();
    Player player3=new Player();


    Trader trader1=new Trader("trader1");
    Trader trader2=new Trader("trader2");
    Trader trader3=new Trader("trader3");

    Trade trade1 = new Trade(player1, trader1);
    Trade trade2 = new Trade(player2, trader2);
    Trade trade3 = new Trade(player3, trader3);


    @Test
    void testSell() throws GameException {
        trader1.backpack.add("thing", 10, 10);
        trader1.backpack.add("thing2", 15, 12);
        trader1.backpack.add("thing3", 20, 14);
        trader1.backpack.add("thing4", 25, 16);
        trader1.backpack.add("thing5", 30, 18);

        assertTrue(player1.b.getThings().isEmpty());
        assertEquals(player1.money, 100);
        trade1.trade("thing");

        assertEquals(player1.money, 90);
        assertEquals(player1.b.getThings().size(), 1);
        assertEquals(player1.b.getThings().get(0).name, "thing");
    }

    @Test
    void testNoEnoughMoney() throws GameException {
        trader1.backpack.add("thing", 101, 10);

        assertTrue(player2.b.getThings().isEmpty());
        assertEquals(player2.money, 100);
        assertThrows(GameException.class, () -> trade2.trade("thing"));
    }

    @Test
    void testNoSuchThing() throws GameException {
        trader1.backpack.add("thing", 10, 10);

        assertTrue(player3.b.getThings().isEmpty());
        assertEquals(player3.money, 100);
        assertThrows(GameException.class, () -> trade3.trade("thing2"));
    }

    @Test
    void testNoThingLeft() throws GameException {
        trader3.backpack.add("thing", 10, 10);

        assertTrue(player3.b.getThings().isEmpty());
        assertEquals(player3.money, 100);
        assertThrows(GameException.class, () -> trade3.trade("thing"));
    }
}
