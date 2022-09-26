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

    void interact(){

    }

    void buy(Backpack.Thing t){
        if (money >= t.price) {
            money -= t.price;
            b.add(t);
        }
    }

    void use(Backpack.Thing t, FighterStat f) throws GameException {
        if (!b.things.remove(t)) {
            throw new GameException("No such thing");
        }
        t.use(f);
    }

    List<Backpack.Thing> getBackpack(){
        return b.getThings();
    }

}
