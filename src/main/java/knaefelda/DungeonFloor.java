package knaefelda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DungeonFloor {
    
    private int combatRating;
    private LootTable lootTable;

    public DungeonFloor(int combatRating) {
        this.combatRating = combatRating;

        // FILLER LOOT TABLE
        HashMap<String, Pair<Double, Integer>> lootMap = new HashMap<>();
        lootMap.put("Bone", new Pair<>(0.5, 1));
        lootMap.put("Gold", new Pair<>(0.2, 1));

        lootTable = new LootTable(lootMap);
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
