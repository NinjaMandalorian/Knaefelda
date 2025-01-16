package knaefelda;
import java.util.ArrayList;

public class Person implements Combatable, Container {

    // Data fields
    private String name;
    private int age;
    private Location residence;

    // Status fields
    private double health = 100;
    private double maxHealth = 100;
    private Location location;

    // Inventory fields
    private ArrayList<Item> inventory = new ArrayList<>();
    private int capacity = 50;

    // Combat fields
    private int combatLevel = 5;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
        this("Person", 20);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Location getResidence() {
        return residence;
    }

    public double getHealth() {
        return health;
    }

    public void damage(double amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return name + ", " + age + " years old";
    }

    @Override
    public int getCombatRating() {
        // Health below 50% reduces combat rating
        double injuryMultiplier = Math.clamp((health/maxHealth)*2, 0, 1);

        return (int) (combatLevel * injuryMultiplier);
    }

    @Override
    public boolean addItem(Item item) {
        if (getWeight() + item.getWeight() <= capacity) {
            return inventory.add(item);
        } else {
            return false;
        }
    }

    @Override
    public boolean removeItem(Item item) {
        return inventory.remove(item);
    }

    @Override
    public Item getItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public Item[] getItems() {
        return inventory.toArray(new Item[0]);
    }

    @Override
    public int getWeight() {
        int totalWeight = 0;
        for (Item item : inventory) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    @Override
    public boolean hasItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItem(Item item) {
        return inventory.contains(item);
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

}