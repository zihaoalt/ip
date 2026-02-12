package whoru.task;

public class Task {

    protected String description;
    protected boolean isDone;
    protected TaskSubclassType taskSubclassType;

    public Task() {
        this("");
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskSubclassType = null;
    }

    public Task(String description, TaskSubclassType taskSubclassType) {
        this.description = description;
        this.isDone = false;
        this.taskSubclassType = taskSubclassType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void updateDoneStatus(Boolean bool) {
        this.isDone = bool;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }
}
