package knaefelda.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import knaefelda.enemies.Enemy;
import knaefelda.enemies.Goblin;

public class DungeonFloor {
    
    private List<Enemy> startingEnemies;
    private List<Enemy> activeEnemies;


    public DungeonFloor() {

    }

    public DungeonFloor(List<Enemy> enemies) {
        this.startingEnemies = new ArrayList<>(enemies);
        activeEnemies = startingEnemies;
    }

    public int getCombatRating() {
        int sum = 0;
        for (Enemy enemy : activeEnemies) {
            if (enemy.isAlive()) sum += enemy.getCombatRating();
        }
        return sum;
    }

    public List<Enemy> getActiveEnemies() {
        return activeEnemies;
    }

    public boolean isCleared() {
        return activeEnemies.stream().allMatch(e -> e.getHealth() <= 0);
    }



    // private int combatRating;
    // private LootTable lootTable;

    // public DungeonFloor(int combatRating) {
    //     this.combatRating = combatRating;

        // // FILLER LOOT TABLE
        // HashMap<String, Pair<Double, Integer>> lootMap = new HashMap<>();
        // lootMap.put("Bone", new Pair<>(0.5, 1));
        // lootMap.put("Gold", new Pair<>(0.2, 1));

        // lootTable = new LootTable(lootMap);
    // }

    // public int getCombatRating() {
    //     return combatRating;
    // }

    // public List<Item> generateLoot() {
    //     ArrayList<Item> items = new ArrayList<>();
    //     lootTable.generateLoot().forEach((item, quantity) -> {
    //         for (int i = 0; i < quantity; i++) {
    //             items.add(item);
    //         }
    //     });
    //     return items;
    // }

}
