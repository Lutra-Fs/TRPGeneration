package src.main;

import java.util.List;

public class Trader extends NPC {
    Backpack backpack;

    Trader(String name, int x, int y, Backpack b) {
        super(name, x, y);
        backpack = b;
    }


    /**
     * Calling this method will change the player's state to trading
     *
     * @param p the player
     * @author Bo ZHANG
     */
    @Override
    void interact(Player p) {
        p.p = Player.PlayerState.TRADING;
    }

    /**
     * get the things in the trader's backpack
     *
     * @return a list, containing all the things in the trader's backpack
     */
    List<Backpack.Thing> getCurrentThings() {
        return backpack.getThings();
    }

    /**
     * sold a thing to the player
     *
     * @param s a string, the name of the thing or the thing's index in the trader's backpack
     * @return the thing with the name or index
     * @throws GameException if the thing is not found
     * @author Bo ZHANG
     */
    Backpack.Thing sell(String s) throws GameException {
        // check if the s is a num and smaller than the size of the backpack
        // if so, return the thing at that index
        // else, return the thing with the name s
        if (s.matches("\\d+")) {
            int index = Integer.parseInt(s);
            if (index < backpack.getThings().size()) {
                return backpack.sell(index);
            } else {
                return backpack.sell(s);
            }
        } else {
            return backpack.sell(s);
        }
    }

    Backpack.Thing sell(Backpack.Thing t) throws GameException {
        return backpack.sell(t);
    }

    /**
     * get the number of things in the trader's backpack
     *
     * @return the number of things in the trader's backpack
     * @author Bo ZHANG
     */
    int getRemainingThingsSize() {
        return backpack.getThings().size();
    }
}
