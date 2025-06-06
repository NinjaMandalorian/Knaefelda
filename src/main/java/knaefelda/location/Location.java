package knaefelda.location;

import knaefelda.Stepable;
import knaefelda.World;

public interface Location extends Position, Stepable {

    public String getName();

    public World getWorld();
    
}
