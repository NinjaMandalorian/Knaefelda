public class DungeonRaid implements Stepable {
    
    public enum RaidStatus {
        ADVANCING,
        RETREATING,
        RESTING,
        COMPLETED,
    }

    private final Dungeon dungeon;
    private final AdventurerParty party;
    
    private int currentFloor;
    private RaidStatus status;

    public DungeonRaid(Dungeon dungeon, AdventurerParty party) {
        this.dungeon = dungeon;
        this.party = party;
    }
    
    public void start() {
        System.out.println("Starting raid on " + dungeon.getName() + " with party " + party.getName());
        System.out.println("Dungeon has " + dungeon.getFloors().size() + " floors");
        System.out.println("Party has " + party.getMembers().size() + " members");
        System.out.println("Party combat rating: " + party.getCombatRating());

        status = RaidStatus.ADVANCING;
    }

    public void end() {
        dungeon.raidEnded(this);
        // Calculate what the loot party members recieved is?
    }

    public void step() {
        System.out.println("Stepping raid in " + dungeon.getName() + " for " + party.getName());

        switch (status) {
            case RETREATING:
                currentFloor--;
                System.out.println("Retreated to floor " + currentFloor);
                if (currentFloor < 1) {
                    end();
                }
                break;
            case RESTING:
                System.out.println("Resting? This hasn't been implemented.");
                break;
            case COMPLETED:
                System.out.println("Raid " + toString() + " completed. Unregistering soon.");
                end();
                break;
            case ADVANCING:
                advance();
                break;        
            default:
                break;
        }
    }

    public void advance() {
        if (currentFloor >= dungeon.getFloors().size()) {
            System.out.println("Raid has already completed.");
            status = RaidStatus.COMPLETED;
            return;
        }

        currentFloor++;
        System.out.println("Advancing to floor " + currentFloor);
        DungeonFloor floor = dungeon.getFloor(currentFloor - 1);

        int floorRating = floor.getCombatRating();
        int partyRating = party.getCombatRating();

        System.out.println("Floor " + currentFloor + " has CR " + floorRating);
        System.out.println("Party has CR " + partyRating);

        double relativeRating = partyRating / (double) floorRating;
        System.out.println("Relative rating: " + relativeRating);

        double deathChance = 1.0 / (50.0 * relativeRating);
        double heavyInjuryChance = 1.0 / (10.0 * relativeRating);
        double injuryChance = 1.0 / (5.0 * relativeRating);
        double minorInjuryChance = 1.0 / (3.0 * relativeRating);

        System.out.println("Death chance: " + deathChance);
        System.out.println("Heavy injury chance: " + heavyInjuryChance);
        System.out.println("Injury chance: " + injuryChance);
        System.out.println("Minor injury chance: " + minorInjuryChance);

    }

    @Override
    public String toString() {
        return dungeon.getName() + "_" + party.getName();
    }

}
