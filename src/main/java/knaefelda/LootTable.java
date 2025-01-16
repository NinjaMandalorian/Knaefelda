package knaefelda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LootTable {

    private class LootTableEntry {
        private Item item;
        private double dropRate;
        private int quantity;
        
        public LootTableEntry(Item item, double dropRate, int quantity) {
            this.item = item;
            this.dropRate = dropRate;
        }

        public Item getItem() {
            return item;
        }

        public double getDropRate() {
            return dropRate;
        }

        public int getQuantity() {
            return quantity;
        }

    }

    private List<LootTableEntry> lootTableEntries = new ArrayList<>();
    private double dropRateSum;

    public LootTable(Map<String, Pair<Double, Integer>> lootTable) {
        lootTable.forEach((itemName, itemData) -> {
            Item item = new Item(itemName, itemName, 0); // TODO : REPLACE WITH REGISTRY LOOKUP
            lootTableEntries.add(new LootTableEntry(item, itemData.getKey(), itemData.getValue()));
        });
    }

    public Map<Item, Integer> generateLoot() {
        return generateLoot(5);
    }

    public Map<Item, Integer> generateLoot(int rolls) {
        Map<Item, Integer> loot = new HashMap<>();
        for (int i = 0; i < rolls; i++) {
            Pair<Item, Integer> roll = generateRoll();
            loot.put(roll.getKey(), loot.getOrDefault(roll.getKey(), 0) + roll.getValue());
        }
        return loot;
    }

    public Pair<Item, Integer> generateRoll() {
        return roll();
    }

    private Pair<Item, Integer> roll() {
        double roll = Math.random() * dropRateSum;
        for (LootTableEntry entry : lootTableEntries) {
            roll -= entry.getDropRate();
            if (roll <= 0) {
                return new Pair<>(entry.getItem(), entry.getQuantity());
            }
        }
        return null;
    }

}
