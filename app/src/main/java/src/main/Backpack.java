package src.main;

import java.util.LinkedList;
import java.util.List;

/**
 * use to store things in backpack
 *
 * @author Bo ZHANG
 */
public class Backpack {
    List<Thing> things;

    /**
     * constructor for backpack
     *
     * @param things things in backpack
     * @author Bo ZHANG
     */
    Backpack(List<Thing> things) {
        this.things = things;
    }


    Backpack() {
        this.things = new LinkedList<>();
    }

    /**
     * return a thing that in the given index of backpack
     *
     * @param index index of thing
     * @return thing
     * @author Bo ZHANG
     */
    Thing sell(int index) {
        return things.remove(index);
    }

    /**
     * get things in backpack
     *
     * @return things
     * @author Bo ZHANG
     */
    List<Thing> getThings() {
        return things;
    }

    /**
     * sell a thing that matches the given name
     *
     * @param name name of thing
     * @return the thing that matches the given name
     * @throws GameException when no such thing
     * @author Bo ZHANG
     */
    Thing sell(String name) throws GameException {
        for (Thing t : things) {
            if (t.name.equals(name)) {
                t.amount--;
                if (t.amount == 0) {
                    things.remove(t);
                }
                return t;
            }
        }
        throw new GameException("No such thing");
    }

    Thing sell(Thing t) throws GameException {
        if (things.contains(t)) {
            t.amount--;
            if (t.amount == 0) {
                things.remove(t);
            }
            return t;
        } else {
            throw new GameException("No such thing");
        }
    }

    /**
     * add a thing to backpack
     *
     * @param t thing to add
     * @author Bo ZHANG
     */
    void add(Thing t) {
        if (things.contains(t)) {
            //amount + 1
            for (Thing thing : things) {
                if (thing.equals(t)) {
                    thing.amount++;
                    break;
                }
            }
        } else {
            things.add(t);
        }
    }

    /**
     * add a thing to backpack
     *
     * @param name thing's name to add
     * @param price of thing to add
     * @param recover the recover effect of this thing
     * @throws GameException when n is negative
     * @author Bo ZHANG
     */
    void add(String name, int price, int recover) {
        things.add(new Thing(name, price, recover));
    }

    /**
     * sell a thing that matches the given name
     *
     * @param name name of thing
     * @return the thing that matches the given name
     * @throws GameException when no such thing
     * @author Bo ZHANG
     */
    Thing getThing(String name) throws GameException {
        for (Thing t : things) {
            if (t.name.equals(name)) {
                return t;
            }
        }
        throw new GameException("No such thing");
    }

    /**
     * use a thing
     *
     * @param t    thing to use
     * @param stat fighter stat
     * @throws GameException when no such thing
     * @author Bo ZHANG
     */
    void use(Thing t, FighterStat stat) throws GameException {
        if (things.contains(t)) {
            t.use(stat);
            for (Thing thing : things) {
                if (thing.equals(t)) {
                    thing.amount--;
                    if (thing.amount == 0) {
                        things.remove(thing);
                    }
                    break;
                }
            }
        } else {
            throw new GameException("No such thing");
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Backpack: \n");
        for (Thing t : things) {
            s.append(t.toString()).append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Backpack backpack)) {
            return false;
        }
        return things.equals(backpack.things);
    }

    @Override
    public int hashCode() {
        return things.hashCode();
    }


    /**
     * Thing class is used to show the price, name for a thing
     *
     * @author Bo ZHANG
     */
    static class Thing {
        final String name;
        final int price;
        final int recover;
        int amount = 1;

        /**
         * constructor for thing
         *
         * @param name    name of thing
         * @param price   price of thing
         * @param recover recover of thing
         * @author Bo ZHANG
         */
        Thing(String name, int price, int recover) {
            this.name = name;
            this.price = price;
            this.recover = recover;
        }

        /**
         * apply the thing to the f given
         *
         * @param f the fighter to apply the thing on
         * @author Bo ZHANG
         */
        void use(FighterStat f) {
            f.addHP(recover);
        }

        /**
         * get the name of thing
         *
         * @return name
         * @author Bo ZHANG
         */
        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name + ": price:" + price + " which can recover " + recover + " HP";
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof Thing thing)) {
                return false;
            }
            return name.equals(thing.name) && price == thing.price && recover == thing.recover;
        }

        @Override
        public int hashCode() {
            return name.hashCode() + price ^ 3 + recover;
        }

    }

}
