package src.main;

public class Location {
    int x;
    int y;
    static int MAX_X;
    static int MAX_Y;

    void setMax(int x, int y) {
        MAX_X = x;
        MAX_Y = y;
    }

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
