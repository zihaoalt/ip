package whoru.command;

import whoru.exception.InvalidTaskNumberException;
import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Task;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

/**
 * Represents a mark command that mark the corresponding task of a number to be Done or Not Done
 */

public class MarkCommand extends Command {
    private final boolean shouldMarkDone;

    public MarkCommand(boolean shouldMarkDone) {
        this.shouldMarkDone = shouldMarkDone;
    }

    /**
     * Parse user input to find task number and update in cache and local storage to be done or not done
     * @param tasks Task list used by the application.
     * @param ui User interface used to display messages.
     * @param storage Storage used to persist task data.
     * @param fullCommand Full command entered by the user.
     * @throws WhoruException if task number out of index
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        //solution below inspired by https://github.com/NUS-CS2113-AY2526-S2/ip/pull/140 during code review practice
        //change from comparing the start of the string to split using space for safety and especially task number parsing
        String[] parts = fullCommand.trim().split("\\s+", 2);
        String numberPart = (parts.length > 1) ? parts[1] : "";
        try {
            int taskIndex = Integer.parseInt(numberPart);

            if (taskIndex > tasks.size() ||  taskIndex < 1) {
                String errorMessage = "Task index out of bounds.";
                throw new InvalidTaskNumberException(errorMessage);
            }

            Task task = tasks.get(taskIndex - 1); //zero base index and 1 base index
            task.updateDoneStatus(shouldMarkDone);
            updateDoneStatus(tasks, storage, ui);
            ui.printMarkResult(task, shouldMarkDone);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Please enter a valid task number");
        }
    }
    /**
     * Returns whether this command terminates the application.
     *
     * @return {@code false}, because marking a task done does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
