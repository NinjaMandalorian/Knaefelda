package knaefelda.location;

public abstract class AbstractLocation implements Location {
    
    protected String name;
    protected double xPos;
    protected double yPos;

    public AbstractLocation(String name, double xPos, double yPos) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public String getName() {
        return name;
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
