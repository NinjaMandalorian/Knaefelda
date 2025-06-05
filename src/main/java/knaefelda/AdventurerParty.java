package knaefelda;

import java.util.ArrayList;
import java.util.Collection;

public class AdventurerParty extends Party implements Container {

    private ArrayList<Item> inventory = new ArrayList<>();
    private int capacity = 200;

    public AdventurerParty(String name) {
        this(name, new ArrayList<Person>());
    }

    public AdventurerParty(String name, Collection<Person> members) {
        super(name);
        for (Person person : members) {
            addMember(person);
        }
    }

    public int getCombatRating() {
        int totalRating = 0;
        for (Person person : getMembers()) {
            totalRating += person.getCombatRating();
        }
        return totalRating;
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

    @Override
    public double getHealth() {
        return getMembers().stream().mapToDouble(Person::getHealth).sum();
    }

    @Override
    public double getAttack() {
        return getMembers().stream().mapToDouble(Person::getAttack).sum();
    }

    @Override
    public void takeDamage(double amount) {
        // WARNING - THIS IS AOE / TOTAL PARTY DAMAGE.
        for (Person member : getMembers()) {
            member.takeDamage(amount);
        }
    }

    @Override
    public boolean isAlive() {
        return getMembers().stream().anyMatch(m -> m.getHealth() > 0);
    }
    
}
