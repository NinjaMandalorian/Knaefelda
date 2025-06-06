package knaefelda.location;

import knaefelda.World;

public abstract class AbstractLocation implements Location {
    
    protected String name;
    protected World world;
    protected double xPos;
    protected double yPos;

    public AbstractLocation(String name, World world, double xPos, double yPos) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public double getX() {
        return xPos;
    }

    @Override
    public double getY() {
        return yPos;
    }

}
