package whoru.storage;
import whoru.task.Deadline;
import whoru.task.Event;
import whoru.task.Task;
import whoru.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final Path FILE_PATH = Paths.get("./data/whoru.txt");
    private static final Path DATA_DIR = Paths.get("./data");
    private static final String FORMAT = " | "; // this looks like space | space

    public static void setupStorageFile(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(DATA_DIR);

        if (Files.notExists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }

        updateTasksList(tasks);
    }

    public static void updateTasksList(ArrayList<Task> tasks) throws IOException {
        List<String> lines = Files.readAllLines(FILE_PATH);

        for (String line : lines) {
            Task task = parseLine(line);
            if (task != null) {
                tasks.add(task);
            }
        }
    }

    private static Task parseLine(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        String[] arguments = trimmed.split("\\s*\\|\\s*");
        if (arguments.length < 3) { // in format T | 0 | description
            return null;
        }

        String type = arguments[0];
        boolean isDone = "1".equals(arguments[1]); // 1 is done 0 is not done
        String description = arguments[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (arguments.length < 4) {
                    return null;
                }
                task = new Deadline(description, arguments[3]);
                break;
            case "E":
                if (arguments.length < 5) {
                    return null;
                }
                task = new Event(description, arguments[3], arguments[4]);
                break;
            default:
                return null;
        }

        task.updateDoneStatus(isDone);
        return task;
    }

    private static String parseLine(Task task) {
        String doneFlag = task.getStatusIcon().equals("X") ? "1" : "0";
        String description = task.getDescription();

        if (task instanceof Todo) {
            return "T" + FORMAT + doneFlag + FORMAT + description;
        }

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D" + FORMAT + doneFlag + FORMAT + description + FORMAT + deadline.getBy();
        }

        if (task instanceof Event) {
            Event event = (Event) task;
            return "E" + FORMAT + doneFlag + FORMAT + description + FORMAT + event.getFrom() + FORMAT + event.getTo();
        }

        return null;
    }


    public static void updateStorageFile(Task task) throws IOException {
        String line = parseLine(task);
        String text = line + System.lineSeparator();
        Files.writeString(FILE_PATH, text,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
}
