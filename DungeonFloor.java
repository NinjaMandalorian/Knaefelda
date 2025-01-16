import java.util.ArrayList;
import java.util.List;

public class DungeonFloor {
    
    private int combatRating;


    public DungeonFloor(int combatRating) {
        this.combatRating = combatRating;
    }

    public int getCombatRating() {
        return combatRating;
    }

    public List<Item> generateLoot() {
        System.out.println("NO LOOT GENERATION YET");
        return new ArrayList<>();
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'generateLoot'");
    }

}
