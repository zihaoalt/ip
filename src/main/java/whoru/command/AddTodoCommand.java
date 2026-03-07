package whoru.command;

import whoru.exception.EmptyDescriptionException;
import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Todo;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

import static whoru.utils.formatter.formatErrorMessage;

/**
 * Represents a command that adds a todo to the list
 */
public class AddTodoCommand extends Command {
    /**
     * Parses the user input and adds a todo task to the task list.
     * Also saves the updated task list and displays the result to the user.
     *
     * @param tasks The tasklist to store the added deadline
     * @param ui The ui object responsible for printing CLI results
     * @param storage The storage object responsible for storing locally
     * @param fullCommand The full string typed by the user
     * @throws WhoruException If the command is missing description.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        String description = fullCommand.substring("todo".length()).trim(); // start from space after todo
        if (description.isEmpty()) {
            String errorMessage = "empty task description";
            throw new EmptyDescriptionException(errorMessage);
        }

        addTask(new Todo(description), tasks, ui, storage);
    }
    /**
     * Returns whether this command terminates the application.
     *
     * @return {@code false}, because adding a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
