package src.main;

import java.util.List;

public class Player {
    String name;
    PlayerState p;
    Location curLoc;
    int money;
    FighterStat fightStat;
    Backpack b;

    enum PlayerState {
        NORMAL,
        FIGHTING,
        TRADING,
        TALKING,
        INTERACTING,
        SAVING
    }


    void save() {

    }

    /**
     * interact with the npc that in the current location
     *
     * @author Bo ZHANG
     */
    void interact() {

    }

    /**
     * buy a thing from the trader
     *
     * @param t the thing to buy
     * @throws GameException if the player doesn't have enough money
     * @author Bo ZHANG
     */
    void buy(Backpack.Thing t) throws GameException {
        if (money >= t.price) {
            money -= t.price;
            b.add(t);
        } else {
            throw new GameException("Not enough money");
        }
    }

    /**
     * use a thing in the backpack while fighting
     *
     * @param t the thing to use
     * @param f the fighter to use the thing on
     * @throws GameException if no such thing
     * @author Bo ZHANG
     */
    void use(Backpack.Thing t, FighterStat f) throws GameException {
        if (!b.things.remove(t)) {
            throw new GameException("No such thing");
        }
        t.use(f);
    }

    /**
     * get the backpack
     *
     * @return backpack
     * @author Bo ZHANG
     */
    Backpack getBackpack() {
        return b;
    }

    /**
     * get the things in backpack
     *
     * @return a list of things in backpack
     * @author Bo ZHANG
     */
    List<Backpack.Thing> getThings() {
        return b.getThings();
    }

}
