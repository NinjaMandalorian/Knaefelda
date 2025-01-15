public class Person implements Combatable {

    // Data fields
    private String name;
    private int age;
    private Location residence;

    // Status fields
    private double health = 100;
    private double maxHealth = 100;
    private Location location;

    // Combat fields
    private int combatLevel = 5;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
        this("Person", 20);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + ", " + age + " years old";
    }

    @Override
    public int getCombatRating() {
        // Health below 50% reduces combat rating
        double injuryMultiplier = Math.clamp((health/maxHealth)*2, 0, 1);

        return (int) (combatLevel * injuryMultiplier);
    }

}