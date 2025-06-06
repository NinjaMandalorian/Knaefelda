package knaefelda.dungeon;

import java.util.ArrayList;
import java.util.List;

import knaefelda.World;
import knaefelda.location.AbstractLocation;
import knaefelda.party.AdventurerParty;

public class Dungeon extends AbstractLocation {
    
    private String name;

    private ArrayList<DungeonFloor> floors = new ArrayList<>();

    // TODO : Limiting output of dungeon to curb spam of resources
    private ArrayList<DungeonRaid> activeRaids = new ArrayList<>();

    public Dungeon(String name, int floors, World world, double xPos, double yPos) {
        super(name, world, xPos, yPos);
        for (int i = 0; i < floors; i++) {
            this.floors.add(DungeonFloors.goblinDungeonFloor(1 + 2*i));
        }
    }

    public String getName() {
        return name;
    }

    public List<DungeonFloor> getFloors() {
        return floors;
    }

    public DungeonFloor getFloor(int index) {
        return floors.get(index);
    }

    public ArrayList<DungeonRaid> getActiveRaids() {
        return activeRaids;
    }

    public DungeonRaid raid(AdventurerParty party) {
        if (!isRaidable()) {
            System.out.println("Dungeon not ready to be raided.");
            return null;
        }
        DungeonRaid raid = new DungeonRaid(this, party);
        activeRaids.add(raid);
        raid.start();
        return raid;
    }

    public void raidEnded(DungeonRaid raid) {
        activeRaids.remove(raid);
    }

    @Override
    public void step() {
        // System.out.println("Stepping dungeon " + name);

        for (int i = 0; i < floors.size(); i++) {
            floors.get(i).step();
        }

        for (int i = 0; i < activeRaids.size(); i++) {
            activeRaids.get(i).step();
        }
    }

    /***
     * Is ready to take a new party?
     * @return Boolean - ready or not.
     */
    public boolean isRaidable() {
        if (floors.get(0).isCleared()) return false;

        // TODO - For now - only 1 party per dungeon
        // If remove this limit, more complex systems may be needed for party raid AI.
        if (activeRaids.size() > 0) return false;

        return true;
    }

}
