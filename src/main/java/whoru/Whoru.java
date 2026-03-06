package whoru;

import whoru.command.Command;
import whoru.parser.Parser;
import whoru.storage.Storage;
import whoru.ui.Ui;
import whoru.tasklist.TaskList;
import whoru.exception.WhoruException;
import java.io.IOException;


public class Whoru {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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
