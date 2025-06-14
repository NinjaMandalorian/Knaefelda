package knaefelda;

import java.util.Scanner;

import knaefelda.dungeon.Dungeon;
import knaefelda.party.AdventurerParty;

public class Game {
    
    private static final int STEPS_PER_SECOND = 2;
    private static final boolean ONE_RAID = true;

    private static boolean isRunning = true;

    private static World world;
    private static WorldManager worldManager;


    public static void main(String[] args) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n");

        world = new World();
        worldManager = new WorldManager(world);
        
        // Run the input loop in a separate thread
        Thread inputThread = new Thread(() -> inputLoop());
        inputThread.start();

        Dungeon startDungeon = new Dungeon(
            "Newbie Dungeon",
            3,
            world,
            5.0,
            3.5
        );
        world.addLocation(startDungeon);

        AdventurerParty party = new AdventurerParty("Yellow Crimson");
        party.addMember(new Person("Sara", 25));
        party.addMember(new Person("Bob", 30));
        party.addMember(new Person("John", 22));

        startDungeon.raid(party);

        while (isRunning) {
            long stepStart = System.currentTimeMillis();

            // System.out.println("Step: " + stepStart);
            worldManager.stepWorld();

            long stepDuration = System.currentTimeMillis() - stepStart;
            long waitTime = (1000 / STEPS_PER_SECOND) - stepDuration;
            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (Exception e) {
                    System.out.println("Error sleeping thread.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Thread went " + waitTime + "over assigned time.");
            }

            // DEBUG AUTO RAID
            if (startDungeon.isRaidable()) {
                if (ONE_RAID) {
                    System.out.println("Would've started new raid.");
                    isRunning = false;
                } else {
                    startDungeon.raid(party);
                }
            }
        }
    }

    private static void inputLoop() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            System.out.println("Enter a command: ");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "world":
                    world.printWorldInfo();
                    break;

                case "quit":
                    System.out.println("Quitting the game...");
                    isRunning = false;
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    public static World getWorld() {
        return world;
    }

    public static WorldManager getWorldManager() {
        return worldManager;
    }

}
