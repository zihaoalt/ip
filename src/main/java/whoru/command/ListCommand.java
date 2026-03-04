package whoru.command;

import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        ui.printList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
