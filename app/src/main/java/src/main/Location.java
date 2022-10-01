package src.main;

public class Location {
    int x;
    int y;
    static int maxX;
    static int maxY;

    static synchronized void setMax(int x, int y) {
        maxX = x;
        maxY = y;
    }

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
