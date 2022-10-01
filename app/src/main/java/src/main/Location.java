package src.main;

public class Location {
    int x;
    int y;
    static int maxX;
    static int maxY;

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
     * constructor of location
     *
     * @param x x
     * @param y y
     * @author Bo ZHANG
     */
    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
