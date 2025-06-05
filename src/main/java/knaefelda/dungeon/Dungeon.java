package knaefelda.dungeon;

import java.util.ArrayList;
import java.util.List;

import knaefelda.AdventurerParty;
import knaefelda.Location;

public class Dungeon implements Location {
    
    private String name;
    private ArrayList<DungeonFloor> floors = new ArrayList<>();

    // TODO : Limiting output of dungeon to curb spam of resources
    private ArrayList<DungeonRaid> activeRaids = new ArrayList<>();

    public Dungeon(String name, int floors) {
        this.name = name;
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

    public void raid(AdventurerParty party) {
        DungeonRaid raid = new DungeonRaid(this, party);
        activeRaids.add(raid);
        raid.start();
    }

    public void raidEnded(DungeonRaid raid) {
        activeRaids.remove(raid);
    }

    @Override
    public void step() {
        System.out.println("Stepping dungeon " + name);

        for (int i = 0; i < floors.size(); i++) {
            floors.get(i).step();
        }

        for (int i = 0; i < activeRaids.size(); i++) {
            activeRaids.get(i).step();
        }
    }

}
