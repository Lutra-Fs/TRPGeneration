package src.main;

import java.util.*;

public class Level {
    // boolean in coordinates: true = wall, false = empty
    int[][] coordinates;
    final int curLevel;
    Map<Location, Boolean> canSave;
    Map<Location, NPC> npcs;
    Set<Location> npcLocation;

    public Level(int curLevel) {
        this.curLevel = curLevel;
        this.coordinates = this.getCoordinates();
        this.canSave = new HashMap<>();
        this.npcs = new HashMap<>();
        this.npcLocation = new HashSet<>();
    }

    // GETs
    public int getCurLevel() {
        return this.curLevel;
    }

    public int[][] getCoordinates() { return this.coordinates; }

    public Map<Location, Boolean> getCanSave() { return this.canSave; }

    public Map<Location, NPC> getNpcs() {
        return this.npcs;
    }

    public Set<Location> getNpcLocation() {
        return this.npcLocation;
    }

    // SETs
    public void setNpcs(Map<Location, NPC> npcs) {
        this.npcs = npcs;
    }

    public void setNpcLocation(Set<Location> npcLocation) {
        this.npcLocation = npcLocation;
    }

    public void setCoordinates(int[][] coordinates) { this.coordinates = coordinates; }

    public void setCanSave(Map<Location, Boolean> canSave) { this.canSave = canSave; }

    // ADDs
    public void addNpc(Location location, NPC npc) {
        this.npcs.put(location, npc);
    }

    public void addNpcLocation(Location location) {
        this.npcLocation.add(location);
    }

    public void addCoordinates(int x, int y, int value) { this.coordinates[x][y] = value; }

    public void addCanSave(Location location, Boolean canSave) { this.canSave.put(location, canSave); }

    // REMOVEs
    public void removeNpc(Location location) {
        this.npcs.remove(location);
    }

    public void removeNpcLocation(Location location) {
        this.npcLocation.remove(location);
    }

    public void removeCoordinates(int x, int y) { this.coordinates[x][y] = 0; }

    public void removeCanSave(Location location) {
        this.canSave.remove(location);
    }

    // OTHER
    public void moveNpc(Location oldLocation, Location newLocation) {
        NPC npc = this.npcs.get(oldLocation);
        this.npcs.remove(oldLocation);
        this.npcs.put(newLocation, npc);
    }

}
