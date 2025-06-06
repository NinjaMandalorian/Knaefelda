package knaefelda.task;

public abstract class AbstractTask implements Task {
    
    protected String name;
    protected Taskable owner;
    protected String description;
    protected boolean completed = false;


    public AbstractTask(String name, Taskable owner, String desc) {
        this.name = name;
        this.owner = owner;
        this.description = desc;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Taskable getTaskable() {
        return owner;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

}
