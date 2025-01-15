import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Location> locations = new ArrayList<>();

    public World() {
    }

    public void addLocation(Location location) {
        locations.add(location);
        Game.getWorldManager().registerStepable(location);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void printWorldInfo() {
        System.out.println("World Locations:");
        for (Location location : locations) {
            System.out.println("- " + location.getName());
        }
    }
}
