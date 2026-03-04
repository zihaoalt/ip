package whoru.command;

import whoru.exception.EmptyDescriptionException;
import whoru.exception.MissingTimeException;
import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Event;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

import static whoru.utils.formatter.formatErrorMessage;

public class AddEventCommand extends Command {
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

        addTask(new Event(description, from, to),  tasks, ui, storage);
    };

    public boolean isExit() {
        return false;
    };
}