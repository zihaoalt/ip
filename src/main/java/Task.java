public class Task {

    protected String description;
    protected boolean isDone;
    protected TaskSubclassType taskSubclassType;

    Task() {
        this("");
    }

    Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskSubclassType = null;
    }

    Task(String description, TaskSubclassType taskSubclassType) {
        this.description = description;
        this.isDone = false;
        this.taskSubclassType = taskSubclassType;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    protected String getDescription() {
        return this.description;
    }

    protected void updateDoneStatus(Boolean bool) {
        this.isDone = bool;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }
}
