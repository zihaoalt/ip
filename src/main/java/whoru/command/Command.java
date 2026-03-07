package whoru.command;

import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Task;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

import java.io.IOException;

/**
 * Represents a general command that can be executed differently
 */
public abstract class Command {
    /**
     * Executes the command using the given task list, user interface, and storage.
     *
     * @param tasks Task list used by the application.
     * @param ui User interface used to display messages.
     * @param storage Storage used to persist task data.
     * @param fullCommand Full command entered by the user.
     * @throws WhoruException If the command cannot be executed successfully.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException;
    /**
     * Returns whether this command exits the application.
     *
     * @return {@code true} if this command terminates the program, {@code false} otherwise.
     */
    public abstract boolean isExit();
    /**
     * Adds the given task to the task list, updates storage, and shows the result to the user.
     *
     * @param task Task to be added.
     * @param tasks Task list to which the task is added.
     * @param ui User interface used to display messages.
     * @param storage Storage used to persist task data.
     */
    public void addTask(Task task, TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.updateStorageFile(task);
            tasks.add(task);
            ui.printAddResult(task, tasks.size());
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
    /**
     * Updates the stored task data after task completion status has changed.
     *
     * @param tasks Task list containing the updated task state.
     * @param storage Storage used to persist task data.
     * @param ui User interface used to display error messages.
     */
    public void updateDoneStatus(TaskList tasks, Storage storage, Ui ui) {
        try {
            storage.updateStorageFile(tasks);
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
