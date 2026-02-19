package whoru;

import whoru.task.Deadline;
import whoru.task.Event;
import whoru.task.Task;
import whoru.task.Todo;
import whoru.task.exception.EmptyDescriptionException;
import whoru.task.exception.InvalidTaskNumberException;
import whoru.task.exception.MissingTimeException;
import whoru.task.exception.EmptyTaskCommandException;
import whoru.storage.Storage;

import java.io.IOException;
import java.util.Scanner;
import static whoru.utils.formatter.formatErrorMessage;
import java.util.ArrayList;


public class Whoru {
    private static final String DIVIDE = "____________________________________________________________";

    private final ArrayList<Task> tasks = new ArrayList<>(10);

    public static void main(String[] args) {
        System.out.println(DIVIDE);
        System.out.println(" Hello! I'm whoru.Whoru");
        System.out.println(" What can I do for you?");
        System.out.println(DIVIDE);

        Whoru whoru = new Whoru();
        try {
            Storage.setupStorageFile(whoru.tasks);
        } catch (IOException e) {
            System.out.println(DIVIDE);
            System.out.println(" Errors occurred during setting up storage file");
            System.out.println(e.getMessage());
            System.out.println(DIVIDE);
        }

        whoru.listenForCommands();
    }


    private void listenForCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine().trim();

            //solution below inspired by https://github.com/NUS-CS2113-AY2526-S2/ip/pull/140 during code review practice
            //change from comparing the start of the string to split using space for safety and especially task number parsing
            if (line.isEmpty()) {
                throw new EmptyTaskCommandException("Start with a non-empty command");
            }
            String[] parts = line.trim().split("\\s+", 2);
            String command = parts[0].toLowerCase(); // use for task number
            String argument1 = (parts.length > 1) ? parts[1] : "";


            if (line.equalsIgnoreCase("bye")) {
                printBye();
                break;
            } else if (line.equalsIgnoreCase("list")) {
                printList();
            } else if (line.startsWith("mark")) {
                try {
                    handleMarkCommand(argument1, true);
                } catch (InvalidTaskNumberException e) {
                    System.out.println(e.getMessage());
                }
            } else if (line.startsWith("unmark")) {
                try {
                    handleMarkCommand(argument1, false);
                } catch (InvalidTaskNumberException e) {
                    System.out.println(e.getMessage());
                }
            } else if (line.startsWith("todo")) {
                try {
                    addTodo(line);
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }

            } else if (line.startsWith("deadline")) {
                try {
                    addDeadline(line);
                } catch (EmptyDescriptionException | MissingTimeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (line.startsWith("event")) {
                try {
                    addEvent(line);
                } catch (EmptyDescriptionException | MissingTimeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (line.startsWith("delete")) {
                try {
                    deleteTask(argument1);
                } catch (InvalidTaskNumberException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                handleUnknownCommand();
            }

        }
    }

    private void deleteTask(String numberPart) throws InvalidTaskNumberException {
        try {
            int taskIndex = Integer.parseInt(numberPart);

            Task task = tasks.remove(taskIndex - 1); //zero base index and 1 base index
            Storage.deleteTask(taskIndex - 1);
            printDeleteResult(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(formatErrorMessage("Please enter a valid task number"));
        } catch (IOException e) {
            System.out.println(DIVIDE);
            System.out.println(" Errors occurred during updating storage file");
            System.out.println(e.getMessage());
            System.out.println(DIVIDE);
        }

    }

    private void printDeleteResult(Task task) {
        System.out.println(DIVIDE);
        System.out.println(" OK, I've deleted this task.");
        System.out.println("   " + task.toString());
        System.out.println(DIVIDE);
    }
    private void addTodo(String input) throws EmptyDescriptionException {
        String description = input.substring("todo".length()).trim(); // start from space after todo
        if (description.isEmpty()) {
            String errorMessage = formatErrorMessage("empty task description");
            throw new EmptyDescriptionException(errorMessage);
        }

        addTask(new Todo(description));
    }

    private void addDeadline(String input) throws EmptyDescriptionException, MissingTimeException {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            String errorMessage = formatErrorMessage("Missing by time");
            throw new MissingTimeException(errorMessage);
        }

        String description = input.substring("deadline".length(), byIndex).trim();
        String by = input.substring(byIndex + "/by".length()).trim();

        if (description.isEmpty() || by.isEmpty()) {
            String errorMessage = formatErrorMessage("empty task description");
            throw new EmptyDescriptionException(errorMessage);
        }

        addTask(new Deadline(description, by));
    }

    private void addEvent(String input) throws EmptyDescriptionException, MissingTimeException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            String errorMessage = formatErrorMessage("Missing from or to time");
            throw new MissingTimeException(errorMessage);
        }

        String description = input.substring("event".length(), fromIndex).trim();
        String from = input.substring(fromIndex + "/from".length(), toIndex).trim();
        String to = input.substring(toIndex + "/to".length()).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            String errorMessage = formatErrorMessage("empty task description");
            throw new EmptyDescriptionException(errorMessage);
        }

        addTask(new Event(description, from, to));
    }

    private void handleUnknownCommand() {
        System.out.println(DIVIDE);
        System.out.println("Sry unknown command detected, try commands like todo/deadline");
        System.out.println(DIVIDE);
        return;
    }

    private void handleMarkCommand(String numberPart, boolean shouldMarkDone) throws InvalidTaskNumberException {
        String command = shouldMarkDone ? "mark" : "unmark";
        try {
            int taskIndex = Integer.parseInt(numberPart);

            if (taskIndex > tasks.size() ||  taskIndex < 1) {
                String errorMessage = formatErrorMessage("Task index out of bounds.");
                throw new InvalidTaskNumberException(errorMessage);
            }

            Task task = tasks.get(taskIndex - 1); //zero base index and 1 base index
            task.updateDoneStatus(shouldMarkDone);
            printMarkResult(task, shouldMarkDone);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(formatErrorMessage("Please enter a valid task number"));
        }

    }

    private int extractTaskNumber(String line) {
        int intStartIndex = -1;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                intStartIndex = i;
                break;
            }
        }

        if (intStartIndex == -1) {
            return -1;
        }

        int intEndIndex = intStartIndex;
        while (intEndIndex < line.length() && Character.isDigit(line.charAt(intEndIndex))) {
            intEndIndex++;
        }
        return Integer.parseInt(line.substring(intStartIndex, intEndIndex));
    }

    private void addTask(Task task) {
        tasks.add(task);
        try {
            Storage.updateStorageFile(task);
        } catch (IOException e) {
            System.out.println(DIVIDE);
            System.out.println(" Errors occurred during setting up storage file");
            System.out.println(e.getMessage());
            System.out.println(DIVIDE);
        }

        printAddResult(task);
    }

    private void printAddResult(Task task) {
        System.out.println(DIVIDE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDE);
    }

    private void printList() {
        System.out.println(DIVIDE);
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }

        System.out.println(DIVIDE);
    }

    private void printMarkResult(Task task, boolean isDone) {
        System.out.println(DIVIDE);
        if (isDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task.toString());
        System.out.println(DIVIDE);
    }

    private void printBye() {
        System.out.println(DIVIDE);
        System.out.println(" ByeBye !");
        System.out.println(DIVIDE);
    }

}
