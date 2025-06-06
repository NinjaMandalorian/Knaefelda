package knaefelda.task;

import knaefelda.Stepable;

public interface Taskable extends Stepable {
    
    public Task getTask();

    public boolean hasActiveTask();

    public void assignTask(Task task) throws IllegalStateException;

}
