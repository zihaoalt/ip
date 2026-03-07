package whoru.command;

import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

/**
 * Represents a command that terminates the program
 */
public class ByeCommand extends Command {
    /**
     * Terminates the program and prints the exiting message
     *
     * @param tasks The tasklist to store the added deadline
     * @param ui The ui object responsible for printing CLI results
     * @param storage The storage object responsible for storing locally
     * @param fullCommand The full string typed by the user
     * @throws WhoruException Not applicable here
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        ui.printBye();
    }
    /**
     * Returns whether this command terminates the application.
     *
     * @return {@code true}, because indeed needs to terminate the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}