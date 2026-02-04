public class Todo extends Task {

    private static TaskSubclassType TODO;

    Todo(String description) {
        super(description, TODO);
    }

    @Override
    public String toString() {
        return  "[T]" + super.toString();
    }
}
