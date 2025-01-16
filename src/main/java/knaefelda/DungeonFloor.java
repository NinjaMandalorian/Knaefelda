package knaefelda;

import java.util.ArrayList;
import java.util.List;

public class DungeonFloor {
    
    private int combatRating;
    private LootTable lootTable;

    public DungeonFloor(int combatRating) {
        this.combatRating = combatRating;
    }

    public int getCombatRating() {
        return combatRating;
    }

    public List<Item> generateLoot() {
        ArrayList<Item> items = new ArrayList<>();
        lootTable.generateLoot().forEach((item, quantity) -> {
            for (int i = 0; i < quantity; i++) {
                items.add(item);
            }
        });
        return items;
    }

}
