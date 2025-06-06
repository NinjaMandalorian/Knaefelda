package knaefelda;
import java.util.ArrayList;

import knaefelda.location.Location;

public class Person implements Combatable, Container {

    // Data fields
    private String name;
    private int age;
    private Location residence;

    // Status fields
    private double health = 25;
    private double maxHealth = 25;
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

    // Data Methods

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Location getResidence() {
        return residence;
    }

    public void setResidence(Location residence) {
        this.residence = residence;
    }

    @Override
    public String toString() {
        return "Person [" + System.identityHashCode(this) + "] " + getName() + " (" + getAge() + ")" + "-" + health + "/" + maxHealth;
    }

    // Status Methods

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public void takeDamage(double amount) {
        health = Math.max(0, health - amount);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
    
    public double getMaxHealth() {
        return maxHealth;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public double getAttack() {
        return combatLevel;
    }

    @Override
    public int getCombatRating() {
        return (int) ((health + 2 * getAttack()) / 5);
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