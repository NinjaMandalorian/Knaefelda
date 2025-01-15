public interface Container {

    public boolean addItem(Item item);

    public boolean removeItem(Item item);

    public Item getItem(String name);

    public Item[] getItems();

    public int getWeight();

    public boolean hasItem(String name);

    public boolean hasItem(Item item);

    public boolean isEmpty();

    public int getCapacity();

}
