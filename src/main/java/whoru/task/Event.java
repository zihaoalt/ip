package whoru.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;
    private static TaskSubclassType EVENT;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description, EVENT);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
    @Override
    public String toString() {
        DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(outFmt) + " to: " + to.format(outFmt) + ")";
    }
}
