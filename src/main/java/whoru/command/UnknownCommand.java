package whoru.command;

import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        ui.handleUnknownCommand();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
