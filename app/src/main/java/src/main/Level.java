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

    /**
     * Constructor for Level
     *
     * @param curLevel    the current level
     * @param maxX        the max x coordinate
     * @param maxY        the max y coordinate
     * @param coordinates the coordinates of the level
     * @param npcs        the npcs in the level
     * @param npcLocation the locations of the npcs
     * @param startLoc    the start location of the level
     * @param endLoc      the end location of the level
     * @author Bo ZHANG
     * @author Jingqi Dou
     */
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


    /**
     * check if the location contains a NPC
     *
     * @param l the location to check
     * @return true if the location contains a NPC, false otherwise
     * @author Bo ZHANG
     * @author Jingqi Dou
     */
    boolean isInteractable(Location l) {
        return npcLocation.contains(l);
    }

    /**
     * get the NPC at the location
     *
     * @param l the location to check
     * @return the NPC at the location
     * @author Bo ZHANG
     * @author Jingqi Dou
     */
    NPC getNPC(Location l) {
        return npcs.get(l);
    }

    /**
     * check if the location is a wall
     *
     * @param l the location to check
     * @return true if the location is a wall, false otherwise
     * @author Bo ZHANG
     * @author Jingqi Dou
     */
    boolean isWall(Location l) {
        return coordinates[l.x][l.y];
    }

    /**
     * check if the location is the end location if so, go to the next level
     *
     * @param p the player
     * @author Bo ZHANG
     * @author Jingqi Dou
     */
    void levelUp(Player p) throws IOException {
        if (p.curLoc.equals(endLoc)) {
            SaveParser.loadNextLevel(this);
            p.curLoc = this.startLoc;
        }
    }

    /**
     * save the path of the game for next level use.
     *
     * @param path the path of the game
     * @author Bo ZHANG
     */
    static synchronized void setGamePath(String path) {
        gamePath = path;
    }


}
