package whoru.command;

import whoru.exception.EmptyDescriptionException;
import whoru.exception.EmptyTaskCommandException;
import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Task;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

/**
 * Represents the find command and print all tasks with keywords in the description
 */
public class FindCommand extends Command {
    /**
     * Parse the user input and finds all tasks with keywords and print the result task list
     * @param tasks Task list used by the application.
     * @param ui User interface used to display messages.
     * @param storage Storage used to persist task data.
     * @param fullCommand Full command entered by the user.
     * @throws WhoruException if fail to parse input
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        TaskList result = new TaskList();

        int firstSpaceIndex = fullCommand.indexOf(" ");
        if (firstSpaceIndex == -1 || firstSpaceIndex == fullCommand.length() - 1) {
            throw new EmptyDescriptionException("Wrong format of command");
        }
        String keyword = fullCommand.substring(firstSpaceIndex + 1).trim();

        for (Task task : tasks.asUnmodifiableList()) {
            if (task.getDescription().contains(keyword)) {
                result.add(task);
            }
        }
        if (result.size() > 0) {
            ui.printList(result);
        } else {
            ui.printNoTask();
        }

    }
    /**
     * Returns whether this command terminates the application.
     *
     * @return {@code false}, because finding task by keyword does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
