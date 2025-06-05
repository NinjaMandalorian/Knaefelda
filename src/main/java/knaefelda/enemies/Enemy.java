package knaefelda.enemies;

import java.util.ArrayList;
import java.util.List;

import knaefelda.Combatable;
import knaefelda.Item;
import knaefelda.LootTable;

public abstract class Enemy implements Combatable {
    protected String name;
    protected double health;
    protected double maxHealth;
    protected double attack;
    protected int combatRating;
    protected LootTable drops;
    protected int dropRolls;

    public Enemy(String name, double health, double attack) {
        this(name, health, attack, null, 0);
    }
    public Enemy(String name, double health, double attack, LootTable drops, int dropRolls) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attack = attack;
        this.drops = drops;
        combatRating = (int) ((health + 2 * attack) / 5);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public double getMaxHealth() {
        return maxHealth;
    }

    @Override
    public double getAttack() {
        return attack;
    }

    @Override
    public void takeDamage(double amount) {
        System.out.println("Enemy [" + System.identityHashCode(this) + "] taking damage: " + amount);
        health = Math.max(health - amount, 0);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public int getCombatRating() {
        return combatRating;
    }

    public List<Item> getLoot() {
        ArrayList<Item> lootList = new ArrayList<>();
        if (drops == null || dropRolls == 0) return lootList;

        drops.generateLoot(dropRolls).forEach((item, quantity) -> {
            for (int i = 0; i < quantity; i++) {
                lootList.add(item);
            }
        });
        return lootList;
    }

    @Override
    public String toString() {
        return "Enemy [" + System.identityHashCode(this) + "] " + getName() + "-" + health + "/" + maxHealth;
    }

}
