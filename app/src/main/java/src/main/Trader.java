package src.main;

import java.util.List;

public class Trader extends NPC {
    Backpack backpack;

    Trader(String name, int x, int y, Backpack b) {
        super(name, x, y);
        backpack = b;
    }


    @Override
    void interact(Player p) {
        p.p = Player.PlayerState.TRADING;
    }

    List<Backpack.Thing> getCurrentThings() {
        return backpack.getThings();
    }

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

    int getRemainingThingsSize() {
        return backpack.getThings().size();
    }
}
