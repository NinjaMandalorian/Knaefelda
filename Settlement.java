import java.util.List;

public abstract class Settlement implements Location {

    private String name;
    private List<Person> residents;

    public Settlement(String name, List<Person> residents) {
        this.name = name;
        this.residents = residents;
    }

    public String getName() {
        return name;
    }

    public List<Person> getResidents() {
        return residents;
    }

}
