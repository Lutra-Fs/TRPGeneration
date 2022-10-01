package src.main;

public class Location {
    static int maxX = 0;
    static int maxY = 0;
    int x;
    int y;

    /**
     * constructor of location
     *
     * @param x x
     * @param y y
     * @author Bo ZHANG
     */
    Location(int x, int y) {
        if (x < 0 || x >= maxX || y < 0 || y >= maxY) {
            throw new IllegalArgumentException("Location out of bound");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * set the max x and y of the map
     *
     * @param x max x
     * @param y max y
     * @author Bo ZHANG
     */
    static synchronized void setMax(int x, int y) {
        maxX = x;
        maxY = y;
    }

    /**
     * get the location of the left of the current location
     *
     * @return the location of the left of the current location
     * @author Bo ZHANG
     */
    Location getLeft() {
        return new Location(x - 1, y);
    }

    /**
     * get the location of the right of the current location
     *
     * @return the location of the right of the current location
     * @author Bo ZHANG
     */
    Location getRight() {
        return new Location(x + 1, y);
    }

    /**
     * get the location of the up of the current location
     *
     * @return the location of the up of the current location
     * @author Bo ZHANG
     */
    Location getUp() {
        return new Location(x, y - 1);
    }


    /**
     * get the location of the down of the current location
     *
     * @return the location of the down of the current location
     * @author Bo ZHANG
     */
    Location getDown() {
        return new Location(x, y + 1);
    }
}
