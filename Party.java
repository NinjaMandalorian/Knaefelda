import java.util.ArrayList;
import java.util.Collection;

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

    public Collection<Person> getMembers() {
        return members;
    }
    
    public String getName() {
        return name;
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
