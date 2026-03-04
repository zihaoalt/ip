package whoru.command;

import whoru.exception.InvalidTaskNumberException;
import whoru.exception.WhoruException;
import whoru.storage.Storage;
import whoru.task.Task;
import whoru.tasklist.TaskList;
import whoru.ui.Ui;

import java.io.IOException;

import static whoru.utils.formatter.formatErrorMessage;

public class DeleteCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String fullCommand) throws WhoruException {
        //solution below inspired by https://github.com/NUS-CS2113-AY2526-S2/ip/pull/140 during code review practice
        //change from comparing the start of the string to split using space for safety and especially task number parsing
        String[] parts = fullCommand.trim().split("\\s+", 2);
        String numberPart = (parts.length > 1) ? parts[1] : "";
        try {
            int taskIndex = Integer.parseInt(numberPart);

            storage.deleteTask(taskIndex - 1);
            Task task = tasks.remove(taskIndex - 1); //zero base index and 1 base index
            ui.printDeleteResult(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Please enter a valid task number");
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
