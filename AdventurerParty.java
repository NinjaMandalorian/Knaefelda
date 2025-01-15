import java.util.ArrayList;
import java.util.Collection;

public class AdventurerParty extends Party {

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
    
}
