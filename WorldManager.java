import java.util.ArrayList;

public class WorldManager {

    private World world;
    private ArrayList<Stepable> stepables = new ArrayList<>();

    public WorldManager(World world) {
        this.world = world;
    }

    public boolean registerStepable(Stepable stepable) {
        return stepables.add(stepable);
    }

    public boolean unregisterStepable(Stepable stepable) {
        return stepables.remove(stepable);
    }

    public void stepWorld() {
        for (Stepable stepable : stepables) {
            stepable.step();
        }
    }
    
}
