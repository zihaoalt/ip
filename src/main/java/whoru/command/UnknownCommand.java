package whoru.command;

import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

/**
 * Handles the unknown command format from the parser to print error message
 */
public class UnknownCommand extends Command {
    /**
     * Prints the corresponding message to remind user
     * @param tasks Task list used by the application.
     * @param ui User interface used to display messages.
     * @param storage Storage used to persist task data.
     * @param fullCommand Full command entered by the user.
     * @throws WhoruException Not Applicable
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        ui.handleUnknownCommand();
    }
    /**
     * Returns whether this command terminates the application.
     *
     * @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
