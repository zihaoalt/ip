package whoru.command;

import whoru.exception.EmptyDescriptionException;
import whoru.exception.EmptyTaskCommandException;
import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Task;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

public class FindCommand extends Command {
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

    @Override
    public boolean isExit() {
        return false;
    }
}
