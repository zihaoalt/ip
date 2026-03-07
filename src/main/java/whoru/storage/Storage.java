package whoru.storage;
import whoru.task.Deadline;
import whoru.task.Event;
import whoru.task.Task;
import whoru.task.Todo;
import whoru.tasklist.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles storage file setup, load, update and delete
 * The tasks stored will be of a specific format
 */
public class Storage {
    private static final String FORMAT = " | "; // this looks like space | space
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Sets up the storage file if it does not exist
     * @throws IOException if IO error happens
     */

    public void setupStorageFile() throws IOException {
        Files.createDirectories(filePath.getParent());

        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * Reads the tasks stored locally when the application starts
     * @return a TaskList containing all the tasks
     * @throws IOException if IO error happens
     */
    public TaskList load() throws IOException {
        setupStorageFile();
        List<String> lines = Files.readAllLines(filePath);
        TaskList tasks = new TaskList();

        for (String line : lines) {
            Task task = parseLine(line);
            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Removes the task whose index is {@code taskIndex}
     * @param taskIndex the index of the task to remove
     * @throws IOException if IO error happens
     */
    public void deleteTask(int taskIndex) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        lines.remove(taskIndex);
        Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE); // overwrite entire file
    }

    /**
     * Parses the stored tasks to be the correct task type
     * @param line raw line from local file
     * @return task of corresponding type
     */
    private Task parseLine(String line) {
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
                task = new Deadline(description, LocalDate.parse(arguments[3]));
                break;
            case "E":
                if (arguments.length < 5) {
                    return null;
                }
                task = new Event(description, LocalDate.parse(arguments[3]), LocalDate.parse(arguments[4]));
                break;
            default:
                return null;
        }

        task.updateDoneStatus(isDone);
        return task;
    }

    /**
     * Parses the task to be string of agreed format for local storage
     * @param task the task to be stored
     * @return a string in agreed format
     */
    private String parseLine(Task task) {
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

    /**
     * Appends the new task at the end of the file
     * @param task the task to be added
     * @throws IOException if IO error happens
     */
    public void updateStorageFile(Task task) throws IOException {
        String line = parseLine(task);
        String text = line + System.lineSeparator();
        Files.writeString(filePath, text,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    /**
     * Overwrites the entire file
     * @param tasks the TaskList to be stored
     * @throws IOException if IO error happens
     */
    public void updateStorageFile(TaskList tasks) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks.asUnmodifiableList()) {
            lines.add(parseLine(task));
        }
        Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE); // overwrite entire file
    }
}
