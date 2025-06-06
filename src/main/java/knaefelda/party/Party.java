package knaefelda.party;

import java.util.ArrayList;
import java.util.List;

import knaefelda.Combatable;
import knaefelda.Person;

public abstract class Party implements Combatable {

    private String name;
    private ArrayList<Person> members = new ArrayList<>();

    public Party(String name) {
        this.name = name;
    }

    public void addMember(Person person) {
        members.add(person);
    }

    public void removeMember(Person person) {
        members.remove(person);
    }

    public List<Person> getMembers() {
        return members;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public double getHealth() {
        return getMembers().stream().mapToDouble(Person::getHealth).sum();
    }

    @Override
    public double getMaxHealth() {
        return getMembers().stream().mapToDouble(Person::getMaxHealth).sum();
    }

    @Override
    public void setHealth(double health) {
        throw new UnsupportedOperationException("You cannot set the health of a party.");
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

    @Override
    public int getCombatRating() {
        int totalRating = 0;
        for (Person person : getMembers()) {
            totalRating += person.getCombatRating();
        }
        return totalRating;
    }

}
