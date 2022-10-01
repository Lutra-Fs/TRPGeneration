package src.main;

import java.util.List;

public class Backpack {
    List<Thing> things;

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
                things.remove(t);
                return t;
            }
        }
        throw new GameException("No such thing");
    }

    /**
     * add a thing to backpack
     *
     * @param t thing to add
     * @author Bo ZHANG
     */

    void add(Thing t) {
        things.add(t);
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
     * constructor
     *
     * @param things things in backpack
     */
    Backpack(List<Thing> things) {
        this.things = things;
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

    class Thing {
        final String name;
        final int price;
        final int recover;

        void use(FighterStat f) {
            f.addHP(recover);
        }

        Thing(String name, int price, int recover) {
            this.name = name;
            this.price = price;
            this.recover = recover;
        }

        public String getName() {
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
