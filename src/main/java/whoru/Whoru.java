package whoru;

import whoru.command.Command;
import whoru.parser.Parser;
import whoru.storage.Storage;
import whoru.ui.Ui;
import whoru.tasklist.TaskList;
import whoru.exception.WhoruException;
import java.io.IOException;

/**
 * Main class of the Whoru application.
 * Initializes the user interface, storage, and task list, and runs the command loop.
 */
public class Whoru {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    /**
     * Creates a {@code Whoru} application using the specified storage file path.
     * Loads existing tasks from storage if available.
     *
     * @param filePath Path of the file used to store task data.
     */
    public Whoru(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.printErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Whoru whoru = new Whoru("data/whoru.txt");
        whoru.run();
    }
    /**
     * Runs the main command loop of the application.
     * Repeatedly reads user input, parses it into a command, executes the command,
     * and continues until an exit command is given.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage, fullCommand);
                isExit = c.isExit();
            } catch (WhoruException e) {
               ui.printErrorMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

}
