package whoru.command;

import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

/**
 * Represents a command that list out all current tasks
 */
public class ListCommand extends Command{
    /**
     * Print all tasks.
     *
     * @param tasks The tasklist to store the added deadline
     * @param ui The ui object responsible for printing CLI results
     * @param storage The storage object responsible for storing locally
     * @param fullCommand The full string typed by the user
     * @throws WhoruException Not applicable.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        ui.printList(tasks);
    }
    /**
     * Returns whether this command terminates the application.
     *
     * @return {@code false}, because printing all tasks does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
