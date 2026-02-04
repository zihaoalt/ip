public class Event extends Task {

    protected String from;
    protected String to;
    private static TaskSubclassType EVENT;

    Event(String description, String from, String to) {
        super(description, EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")" ;
    }
}
