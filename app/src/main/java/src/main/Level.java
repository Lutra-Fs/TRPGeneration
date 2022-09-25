package src.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Level {
    // boolean in coordinates: true = wall, false = empty
    Map<Location, Boolean> coordinates;
    final int curLevel;
    Set<Boolean> canSave;
    Map<Location, NPC> npcs;
    Set<Location> npcLocation;

    public Level(int curLevel) {
        this.curLevel = curLevel;
        this.coordinates = new HashMap<>();
        this.canSave = new HashSet<>();
        this.npcs = new HashMap<>();
        this.npcLocation = new HashSet<>();
    }

    // GETs
    // get current level
    public int getCurLevel() {
        return this.curLevel;
    }

    public Map<Location, Boolean> getCoordinates() {
        return this.coordinates;
    }

    public Set<Boolean> getCanSave() { return this.canSave; }

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

    public void setCoordinates(Map<Location, Boolean> coordinates) {
        this.coordinates = coordinates;
    }

    public void setCanSave(Set<Boolean> canSave) {
        this.canSave = canSave;
    }

    // ADDs
    public void addNpc(Location location, NPC npc) {
        this.npcs.put(location, npc);
    }

    public void addNpcLocation(Location location) {
        this.npcLocation.add(location);
    }

    public void addCoordinate(Location location, Boolean isWall) {
        this.coordinates.put(location, isWall);
    }

    public void addCanSave(Boolean canSave) {
        this.canSave.add(canSave);
    }

    // REMOVEs
    public void removeNpc(Location location) {
        this.npcs.remove(location);
    }

    public void removeNpcLocation(Location location) {
        this.npcLocation.remove(location);
    }

    public void removeCoordinate(Location location) {
        this.coordinates.remove(location);
    }

    public void removeCanSave(Location location) {
        this.canSave.remove(location);
    }

    // OTHER
    public void moveNpc(Location oldLocation, Location newLocation) {
        NPC npc = this.npcs.get(oldLocation);
        this.npcs.remove(oldLocation);
        this.npcs.put(newLocation, npc);
    }

    public void moveCoordinate(Location oldLocation, Location newLocation) {
        Boolean isWall = this.coordinates.get(oldLocation);
        this.coordinates.remove(oldLocation);
        this.coordinates.put(newLocation, isWall);
    }
}
