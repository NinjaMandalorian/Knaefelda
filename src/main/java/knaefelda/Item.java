package knaefelda;

/**
 * Item class
 * Constitutes any item held by a Container
 */
public class Item {

    private String id;
    private String name;
    private String description;
    private int weight;

    public Item(String id, String name, String description, int weight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public Item(String name, String description, int weight) {
        this.id = name.stripLeading().stripTrailing().replace(" ", "_").toLowerCase();
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }
    
}
