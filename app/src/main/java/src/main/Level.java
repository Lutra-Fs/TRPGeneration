package src.main;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * level class for the game
 *
 * @author Bo ZHANG
 * @author Jingqi DOU
 */
public class Level {
    static String gamePath;
    final int curLevel;
    final Location startLoc;
    final Location endLoc;
    // boolean in coordinates: true = wall, false = empty
    boolean[][] coordinates;
    Map<Location, NPC> npcs;
    Set<Location> npcLocation;
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
    Level(int curLevel, int maxX, int maxY, boolean[][] coordinates, Map<Location, NPC> npcs, Set<Location> npcLocation, Location startLoc, Location endLoc) {
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
     * save the path of the game for next level use.
     *
     * @param path the path of the game
     * @author Bo ZHANG
     */
    static synchronized void setGamePath(String path) {
        gamePath = path;
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
     * get the surrounding locations of the location
     *
     * @param l the location to check
     * @return a String array of the surrounding locations
     * @author Bo ZHANG
     * @see App#gameToPanel
     */
    String[][] getSurronding(Location l) {
        String[][] surronding = new String[5][5];
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (l.x + i >= Location.maxX || l.x + i < 0 || l.y + j >= Location.maxY || l.y + j < 0) {
                    surronding[i + 2][j + 2] = "";
                    continue;
                }
                Location loc = new Location(l.x + i, l.y + j);
                if (isWall(loc)) {
                    surronding[i + 2][j + 2] = "wall";
                } else if (isInteractable(loc)) {
                    surronding[i + 2][j + 2] = "npc";
                } else {
                    surronding[i + 2][j + 2] = "empty";
                }
            }
        }
        return surronding;
    }
}
