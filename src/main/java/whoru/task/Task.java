package whoru.task;

/**
 * Represents the general Task with description and done status
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected TaskSubclassType taskSubclassType;

    public Task() {
        this("");
    }

    /**
     * Initiates a Task with description
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskSubclassType = null;
    }

    /**
     * Initiates a Task with description and task type
     * @param description the description of the task
     * @param taskSubclassType the type of the task
     */
    public Task(String description, TaskSubclassType taskSubclassType) {
        this.description = description;
        this.isDone = false;
        this.taskSubclassType = taskSubclassType;
    }

    /**
     * Gets the done status of the task
     * @return X if done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates the done status of the task
     * @param bool the new done status to be updated to
     */
    public void updateDoneStatus(Boolean bool) {
        this.isDone = bool;
    }
    /**
     * Converts a task to string
     * @return a string for printing
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }
}
