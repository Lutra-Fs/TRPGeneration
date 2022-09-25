package src.main;

import java.util.List;

public class Backpack {
    List<Thing> things;
    Thing sell(int index){
        return things.remove(index);
    }
    void buy(Thing t){
        things.add(t);
    }
    List<Thing> getThings(){
        return things;
    }
    Thing sell(Thing t){
        things.remove(t);
        return t;
    }
    Thing sell(String name) throws GameException {
        for (Thing t : things) {
            if (t.name.equals(name)) {
                things.remove(t);
                return t;
            }
        }
        throw new GameException("No such thing");
    }



    class Thing{
        final String name;
        final int price;
        final int recover;
        void use(FighterState f){
            f.addHP(recover);
        }

        Thing(String name, int price, int recover){
            this.name = name;
            this.price = price;
            this.recover = recover;
        }

        @Override
        public String toString() {
            return name + ": price:" + price + " which can recover " + recover + " HP";
        }

    }

}
