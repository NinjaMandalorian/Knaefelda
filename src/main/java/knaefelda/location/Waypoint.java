package knaefelda.location;

import knaefelda.World;

public class Waypoint extends AbstractLocation {

    public Waypoint(String name, World world, double xPos, double yPos) {
        super(name, world, xPos, yPos);
    }

    @Override
    public void step() {
        return;
    }
    
}
