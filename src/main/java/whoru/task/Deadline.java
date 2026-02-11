package whoru.task;

public class Deadline extends Task {

    protected String by;
    private static TaskSubclassType DEADLINE;

    public Deadline(String description, String by) {
        super(description, DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
