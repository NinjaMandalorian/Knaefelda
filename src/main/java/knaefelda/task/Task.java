package knaefelda.task;

import knaefelda.Stepable;

public interface Task extends Stepable {
    
    public String getName();

    public Taskable getTaskable();

    public String getDescription();

    public boolean isCompleted();

}
