package whoru.command;

import whoru.exception.EmptyDescriptionException;
import whoru.exception.MissingTimeException;
import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Deadline;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static whoru.utils.formatter.formatErrorMessage;

/**
 * Represents a command that adds a deadline task.
 */
public class AddDeadlineCommand extends Command {
    /**
     * Parses the user input and adds a deadline task to the task list.
     * Also saves the updated task list and displays the result to the user.
     *
     * @param tasks The tasklist to store the added deadline
     * @param ui The ui object responsible for printing CLI results
     * @param storage The storage object responsible for storing locally
     * @param fullCommand The full string typed by the user
     * @throws WhoruException If the command is missing a deadline description, missing a {@code /by} time, or uses an invalid date format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        int byIndex = fullCommand.indexOf("/by");
        if (byIndex == -1) {
            String errorMessage = "Missing by time";
            throw new MissingTimeException(errorMessage);
        }

        String description = fullCommand.substring("deadline".length(), byIndex).trim();
        String by = fullCommand.substring(byIndex + "/by".length()).trim();

        if (description.isEmpty()) {
            String errorMessage = "empty task description";
            throw new EmptyDescriptionException(errorMessage);
        }
        if (by.isEmpty()) {
            String errorMessage = "empty by time";
            throw new MissingTimeException(errorMessage);
        }

        LocalDate byDate;
        try {
            byDate = LocalDate.parse(by); // expects yyyy-mm-dd
        } catch (DateTimeParseException e) {
            throw new MissingTimeException("Use date format yyyy-mm-dd");
        }

        addTask(new Deadline(description, byDate), tasks, ui, storage);
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
