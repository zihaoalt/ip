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

public class AddDeadlineCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        int byIndex = fullCommand.indexOf("/by");
        if (byIndex == -1) {
            String errorMessage = "Missing by time";
            throw new MissingTimeException(errorMessage);
        }

        String description = fullCommand.substring("deadline".length(), byIndex).trim();
        String by = fullCommand.substring(byIndex + "/by".length()).trim();

        if (description.isEmpty() || by.isEmpty()) {
            String errorMessage = "empty task description";
            throw new EmptyDescriptionException(errorMessage);
        }

        LocalDate byDate;
        try {
            byDate = LocalDate.parse(by); // expects yyyy-mm-dd
        } catch (DateTimeParseException e) {
            throw new MissingTimeException("Use date format yyyy-mm-dd");
        }

        addTask(new Deadline(description, byDate), tasks, ui, storage);
    };

    public boolean isExit() {
        return false;
    };
}
