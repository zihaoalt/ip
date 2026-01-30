public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this("");
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public String getPrintingString() {
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }
}
