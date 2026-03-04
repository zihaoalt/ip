package whoru.command;

import whoru.exception.EmptyDescriptionException;
import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Todo;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

import static whoru.utils.formatter.formatErrorMessage;

public class AddTodoCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        String description = fullCommand.substring("todo".length()).trim(); // start from space after todo
        if (description.isEmpty()) {
            String errorMessage = "empty task description";
            throw new EmptyDescriptionException(errorMessage);
        }

        addTask(new Todo(description), tasks, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
