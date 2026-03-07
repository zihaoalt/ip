package whoru.task;

public class Todo extends Task {

    private static TaskSubclassType TODO;

    /**
     * Initiates a todo task with description
     * @param description the description of the todo
     */
    public Todo(String description) {
        super(description, TODO);
    }
    /**
     * Converts a todo to string
     * @return a string for printing
     */
    @Override
    public String toString() {
        return  "[T]" + super.toString();
    }
}
