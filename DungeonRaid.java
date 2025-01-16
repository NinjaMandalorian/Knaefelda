import java.util.List;

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

        // Advance logic
        // - Push forward
        // - Roll for each party member on injuries
        // - Add floor's loot
        // - If anybody below 20% health, retreat
        // - If current CR is less than 50% next floor, retreat.

        currentFloor++;
        System.out.println("Advancing to floor " + currentFloor);
        DungeonFloor floor = dungeon.getFloor(currentFloor - 1);

        int floorRating = floor.getCombatRating();
        int partyRating = party.getCombatRating();

        // System.out.println("Floor " + currentFloor + " has CR " + floorRating);
        // System.out.println("Party has CR " + partyRating);

        double relativeRating = partyRating / (double) floorRating;
        System.out.println("Relative rating: " + relativeRating);

        double deathChance = 1.0 / (50.0 * relativeRating); // 1 in 50 on equal rating - 100% health loss
        double heavyInjuryChance = 1.0 / (10.0 * relativeRating); // 1 in 10 on equal rating - 60 health loss
        double injuryChance = 1.0 / (5.0 * relativeRating); // 1 in 5 on equal rating - 30 health loss
        double minorInjuryChance = 1.0 / (3.0 * relativeRating); // 1 in 3 on equal rating - 10 health loss

        // System.out.println("Death chance: " + deathChance);
        // System.out.println("Heavy injury chance: " + heavyInjuryChance);
        // System.out.println("Injury chance: " + injuryChance);
        // System.out.println("Minor injury chance: " + minorInjuryChance);

        for (int i = 0; i < party.getMembers().size(); i++) {
            Person person = party.getMembers().get(i);
            double roll = Math.random();
            if (roll < deathChance) {
                System.out.println(person.getName() + " died on floor " + currentFloor);
                person.setHealth(0.0);
                party.removeMember(person);
            } else if (roll < heavyInjuryChance) {
                System.out.println(person.getName() + " was heavily injured on floor " + currentFloor);
                person.setHealth(person.getHealth() * 0.4);
            } else if (roll < injuryChance) {
                System.out.println(person.getName() + " was injured on floor " + currentFloor);
                person.setHealth(person.getHealth() * 0.7);
            } else if (roll < minorInjuryChance) {
                System.out.println(person.getName() + " was lightly injured on floor " + currentFloor);
                person.setHealth(person.getHealth() * 0.9);
            }
            
            if (person.getHealth() < 0.2 * person.getMaxHealth()) {
                System.out.println(person.getName() + " is below 20% health. Retreating.");
                status = RaidStatus.RETREATING;
            }
        }

        // Loot generation
        List<Item> loot = floor.generateLoot();
        while (loot.size() > 0) {
            Item item = loot.remove(0);
            if (party.addItem(item))
                System.out.println("Party looted " + item.getName());
            else
                System.out.println("Party inventory full. Discarding " + item.getName());
        }
            

        if (currentFloor >= dungeon.getFloors().size()) {
            System.out.println("Raid completed all floors.");
            status = RaidStatus.COMPLETED;
            return;
        } else if (party.getCombatRating() < 0.5 * dungeon.getFloor(currentFloor).getCombatRating()) {
            System.out.println("Party CR is less than 50% of next floor CR. Retreating.");
            status = RaidStatus.RETREATING;
        }

    }

    @Override
    public String toString() {
        return dungeon.getName() + "_" + party.getName();
    }

}
