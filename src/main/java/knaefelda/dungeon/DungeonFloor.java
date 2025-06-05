package knaefelda.dungeon;

import java.util.ArrayList;
import java.util.List;

import knaefelda.Stepable;
import knaefelda.enemies.Enemy;

public class DungeonFloor implements Stepable {
    
    // Enemies
    private List<Enemy> enemies;

    // Respawn information
    long respawnTime = 20 * 1000; // 20s
    boolean respawning = false;
    long nextRespawn = 0;


    public DungeonFloor() {
        this(new ArrayList<>());
    }

    public DungeonFloor(List<Enemy> enemies) {
        this.enemies = new ArrayList<>(enemies);
    }

    public int getCombatRating() {
        int sum = 0;
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) sum += enemy.getCombatRating();
        }
        return sum;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isCleared() {
        return enemies.stream().allMatch(e -> e.getHealth() <= 0);
    }

    @Override
    public void step() {
        if (respawning) {
            if (System.currentTimeMillis() > nextRespawn) {
                respawnEnemies();
            }
        } else {
            boolean floorWiped = enemies.stream().allMatch(e -> !e.isAlive());
            if (floorWiped) {
                respawning = true;
                nextRespawn = System.currentTimeMillis() + respawnTime;
            }
        }

    }

    private void respawnEnemies() {
        System.out.println("RESPAWNING ENEMIES");
        for (Enemy enemy : enemies) {
            enemy.setHealth(enemy.getMaxHealth());
        }
    }

}
