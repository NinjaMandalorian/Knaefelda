package knaefelda.enemies;

import java.util.HashMap;

import knaefelda.LootTable;
import knaefelda.Pair;

public class Goblin extends Enemy {

    public Goblin() {
        super(
            "Goblin",
            10,
            2,
            new LootTable(new HashMap<>() {{
                put("Bone", new Pair<>(0.5, 1));
                put("Gold", new Pair<>(0.2, 1));
            }}),
            2);
    }

}
