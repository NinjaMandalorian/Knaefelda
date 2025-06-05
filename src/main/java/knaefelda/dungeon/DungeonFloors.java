package knaefelda.dungeon;

import java.util.ArrayList;
import java.util.List;

import knaefelda.enemies.Enemy;
import knaefelda.enemies.Goblin;

public class DungeonFloors {
    
    public static DungeonFloor goblinDungeonFloor(int goblinCount) {
        List<Enemy> goblins = new ArrayList<>();
        for (int i = 0; i < goblinCount; i++) {
            goblins.add(new Goblin());
        }
        return new DungeonFloor(goblins);
    }
    
}
