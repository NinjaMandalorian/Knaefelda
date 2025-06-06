package knaefelda.location;

import knaefelda.Stepable;

public interface Location extends Stepable {

    public String getName();
    
    public double getX();

    public double getY();
    
}
