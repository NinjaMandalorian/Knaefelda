package knaefelda;

public interface Combatable {
    
    public String getName();

    public int getCombatRating();
    
    public double getHealth();

    public double getAttack();

    public void takeDamage(double amount);

    public boolean isAlive();

}
