package knaefelda;

import java.util.List;

import knaefelda.location.AbstractLocation;

public abstract class Settlement extends AbstractLocation {

    private List<Person> residents;

    public Settlement(String name, World world, List<Person> residents, double xPos, double yPos) {
        super(name, world, xPos, yPos);
        this.residents = residents;
    }

    public List<Person> getResidents() {
        return residents;
    }

}
