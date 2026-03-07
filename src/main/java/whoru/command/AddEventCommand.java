package whoru.command;

import whoru.exception.EmptyDescriptionException;
import whoru.exception.MissingTimeException;
import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Event;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static whoru.utils.formatter.formatErrorMessage;

/**
 * Represents a command that adds an event task
 */
public class AddEventCommand extends Command {
    /**
     * Parses the user input and adds an event task to the task list.
     * Also saves the updated task list and displays the result to the user.
     *
     * @param tasks The tasklist to store the added deadline
     * @param ui The ui object responsible for printing CLI results
     * @param storage The storage object responsible for storing locally
     * @param fullCommand The full string typed by the user
     * @throws WhoruException If the command is missing description, missing a {@code /from} or {@code /to} time, or uses an invalid date.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        int fromIndex = fullCommand.indexOf("/from");
        int toIndex = fullCommand.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            String errorMessage = "Missing from or to time";
            throw new MissingTimeException(errorMessage);
        }

        String description = fullCommand.substring("event".length(), fromIndex).trim();
        String from = fullCommand.substring(fromIndex + "/from".length(), toIndex).trim();
        String to = fullCommand.substring(toIndex + "/to".length()).trim();


        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            String errorMessage = "empty task description";
            throw new EmptyDescriptionException(errorMessage);
        }

        try {
            LocalDate fromDt = LocalDate.parse(from);
            LocalDate toDt = LocalDate.parse(to);

            if (toDt.isBefore(fromDt)) {
                throw new MissingTimeException("to is before from time, wrong date format");
            }

            addTask(new Event(description, fromDt, toDt),  tasks, ui, storage);
        } catch (DateTimeParseException e) {
            throw new MissingTimeException("Use date format yyyy-mm-dd");
        }

    };

    /**
     * Returns whether this command terminates the application.
     *
     * @return {@code false}, because adding a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    };
}