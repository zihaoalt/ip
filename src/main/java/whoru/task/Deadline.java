package whoru.task;

import whoru.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static whoru.task.TaskSubclassType.DEADLINE;

/**
 * Represents the deadline task with a {@code by} time, description and done status
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUT_FMT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description, DEADLINE);
        this.by = by;
    }

    /**
     * Gets the {@code by} time of the deadline
     * @return {@code by} time in LocalDate
     */
    public LocalDate getBy() { return by; }

    /**
     * Converts a deadline to string
     * @return a string for printing
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUT_FMT) + ")";
    }
}
