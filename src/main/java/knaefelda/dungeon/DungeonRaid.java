package knaefelda.dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import knaefelda.Combatable;
import knaefelda.Item;
import knaefelda.Person;
import knaefelda.Stepable;
import knaefelda.enemies.Enemy;
import knaefelda.party.AdventurerParty;

public class DungeonRaid implements Stepable {

    public enum RaidStatus {
        ADVANCING,
        FIGHTING,
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
        status = RaidStatus.FIGHTING;
        currentFloor = 0;

        System.out.println("Starting raid on " + dungeon.getName() + " with party " + party.getName());
        System.out.println("Dungeon has " + dungeon.getFloors().size() + " floors");
        System.out.println("Party has " + party.getMembers().size() + " members");
        System.out.println("Party combat rating: " + party.getCombatRating());

    }

    public void end() {
        dungeon.raidEnded(this);
        // TODO - Calculate what the loot party members recieved is?
        // Or is that all added live mid-dungeon to inventories? may be better.

        List<Person> members = party.getMembers();
        long alive = members.stream().filter(m -> m.isAlive()).count();
        double partyHp = members.stream().mapToDouble(Person::getHealth).sum();
        double partyMaxHp = members.stream().mapToDouble(Person::getMaxHealth).sum();
        String lootString = Arrays.stream(party.getItems())
            .collect(Collectors.groupingBy(Item::getName, Collectors.counting()))
            .entrySet().stream()
            .map(e -> " - " + e.getKey() + ": " + e.getValue())
            .collect(Collectors.joining("\n"));

        String raidSummary = "--------------\n RAID SUMMARY\n--------------";
        raidSummary += "\nMembers: " + members.size();
        raidSummary += "\nAlive: " + alive + "/" + members.size();
        raidSummary += "\nTotal HP: " + partyHp + "/" + partyMaxHp;
        raidSummary += "\nLoot:\n" + lootString;
        System.out.println(raidSummary);
    }

    public void step() {
        System.out.println("Stepping raid in " + dungeon.getName() + " for " + party.getName());

        // TODO - Think about global time - How many dungeon loops occur during one "global step"
        // Should larger events occur less regularly step-wise? One "step" univseral smallest time-unit?

        switch (status) {
            case RETREATING: {
                currentFloor--;
                System.out.println("Retreated to floor " + currentFloor);
                if (currentFloor < 1) {
                    end();
                }
                break;
            }
            case RESTING: {
                System.out.println("Resting? This hasn't been implemented.");
                break;
            }
            case COMPLETED: {
                System.out.println("Raid " + toString() + " completed. Unregistering soon.");
                end();
                break;
            }
            case ADVANCING: {
                currentFloor++;
                if (currentFloor >= dungeon.getFloors().size()) {
                    System.out.println("Raid has already completed.");
                    status = RaidStatus.COMPLETED;
                    return;
                } else {
                    status = RaidStatus.FIGHTING;
                }
                break;
            }
            case FIGHTING: {
                fighting();
                break;
            }
            default:
                break;
        }
    }

    public void fighting() {
        System.out.println("Fighting!");
        DungeonFloor floor = dungeon.getFloor(currentFloor);
        List<Person> adventurers = party.getMembers();
        List<Enemy> enemies = floor.getEnemies();

        List<Combatable> turnOrder = new ArrayList<>();
        turnOrder.addAll(adventurers);
        turnOrder.addAll(enemies);

        // Combatants fight
        for (Combatable entity : turnOrder) {
            if (!entity.isAlive()) continue;

            List<? extends Combatable> opponents = (entity instanceof Person) ? enemies : adventurers;
            List<? extends Combatable> aliveOpponents = opponents.stream().filter(o -> o.isAlive()).toList();

            if (aliveOpponents.isEmpty()) {
                continue;
            }

            Combatable target = aliveOpponents.get((int)(Math.random() * aliveOpponents.size()));

            double damage = entity.getAttack();
            target.takeDamage(damage);
            System.out.println(entity.getName() + " hits " + target.getName() + " for " + damage);

            if (target instanceof Enemy enemy && !enemy.isAlive()) {
                // Loot
                System.out.println(enemy.getName() + " defeated!");
                for (Item item : enemy.getLoot()) {
                    if (party.addItem(item)) {
                        System.out.println("Looted " + item.getName());
                    } else {
                        System.out.println("Party is full, discarded " + item.getName());
                    }
                }
            } else if (target instanceof Person person && !person.isAlive()) {
                System.out.println(person.getName() + " died!");
                party.removeMember(person); // TODO - Add "memberDied" for graceful transfer of items??
            }

        }

        // Post-combat checks
        boolean enemiesDefeated = enemies.stream().allMatch(e -> !e.isAlive());
        boolean totalPartyWipe = !party.getMembers().stream().anyMatch(m -> m.isAlive());

        if (enemiesDefeated) {
            System.out.println("Floor cleared!");
            status = RaidStatus.ADVANCING;
        } else if (totalPartyWipe) {
            System.out.println("Party died. Raid failed.");
            status = RaidStatus.COMPLETED;
        }

    }

    @Override
    public String toString() {
        return dungeon.getName() + "_" + party.getName();
    }

}
