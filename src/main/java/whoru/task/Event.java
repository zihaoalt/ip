package whoru.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents the event task with a {@code from} & {@code to} time, description and done status
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;
    private static TaskSubclassType EVENT;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description, EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the {@code from} time of the event
     * @return the {@code from} time in LocalDate format
     */
    public LocalDate getFrom() {
        return from;
    }
    /**
     * Gets the {@code to} time of the event
     * @return the {@code to} time in LocalDate format
     */
    public LocalDate getTo() {
        return to;
    }
    /**
     * Converts an event to string
     * @return a string for printing
     */
    @Override
    public String toString() {
        DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(outFmt) + " to: " + to.format(outFmt) + ")";
    }
}
