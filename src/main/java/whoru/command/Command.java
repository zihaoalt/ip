package whoru.command;

import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Task;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException;

    public abstract boolean isExit();

    public void addTask(Task task, TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.updateStorageFile(task);
            tasks.add(task);
            ui.printAddResult(task, tasks.size());
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    public void updateDoneStatus(TaskList tasks, Storage storage, Ui ui) {
        try {
            storage.updateStorageFile(tasks);
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
