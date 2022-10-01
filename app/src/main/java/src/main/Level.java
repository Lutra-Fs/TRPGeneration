package src.main;

import java.io.IOException;
import java.util.*;

public class Level {
    // boolean in coordinates: true = wall, false = empty
    boolean[][] coordinates;
    final int curLevel;
    Map<Location, NPC> npcs;
    Set<Location> npcLocation;

    final Location startLoc;

    final Location endLoc;

    static String gamePath;

    int maxX;
    int maxY;

    public Level(int curLevel, int maxX, int maxY, boolean[][] coordinates, Map<Location, NPC> npcs, Set<Location> npcLocation, Location startLoc, Location endLoc) {
        this.curLevel = curLevel;
        this.coordinates = coordinates;
        this.npcs = npcs;
        this.npcLocation = npcLocation;
        this.startLoc = startLoc;
        this.endLoc = endLoc;
        this.maxX = maxX;
        this.maxY = maxY;
        Location.setMax(maxX, maxY);
    }

    boolean interactable(Location l) {
        return npcLocation.contains(l);
    }

    NPC getNPC(Location l) {
        return npcs.get(l);
    }

    boolean isWall(Location l) {
        return coordinates[l.x][l.y];
    }

    void levelUp(Player p) throws IOException {
        if (p.curLoc.equals(endLoc)) {
            SaveParser.loadNextLevel(this);
            p.curLoc = this.startLoc;
        }
    }

    synchronized static void setGamePath(String path) {
        gamePath = path;
    }


}
