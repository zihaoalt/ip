package whoru.ui;

import whoru.exception.EmptyTaskCommandException;
import whoru.exception.WhoruException;
import whoru.task.Task;
import whoru.tasklist.TaskList;

import java.util.Scanner;

import static whoru.utils.formatter.formatErrorMessage;

/**
 * Handles user interaction and displays messages in the command line interface.
 */
public class Ui {
    private static final String DIVIDE = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Creates a {@code Ui} object that reads user input from terminal.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the result message after a task has been deleted.
     *
     * @param task Task that was deleted.
     */
    public void printDeleteResult(Task task) {
        System.out.println(DIVIDE);
        System.out.println(" OK, I've deleted this task.");
        System.out.println("   " + task.toString());
        System.out.println(DIVIDE);
    }

    /**
     * Prints a message indicating that the user entered an unknown command.
     */
    public void handleUnknownCommand() {
        System.out.println(DIVIDE);
        System.out.println("Sry unknown command detected, try commands like todo/deadline");
        System.out.println(DIVIDE);
        return;
    }

    /**
     * Prints the result message after a task has been added.
     *
     * @param task Task added.
     * @param size Number of tasks currently in the task list.
     */
    public void printAddResult(Task task, int size) {
        System.out.println(DIVIDE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println(DIVIDE);
    }

    /**
     * Prints the exiting message.
     */
    public void printBye() {
        System.out.println(DIVIDE);
        System.out.println(" ByeBye !");
        System.out.println(DIVIDE);
    }

    /**
     * Prints all tasks currently stored in the task list.
     *
     * @param tasks Task list containing the tasks to display.
     * @throws WhoruException If a task cannot be retrieved from the task list.
     */
    public void printList(TaskList tasks) throws WhoruException {
        System.out.println(DIVIDE);
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }

        System.out.println(DIVIDE);
    }

    /**
     * Prints the result message after a task's done status has been updated.
     *
     * @param task Task whose status was updated.
     * @param isDone Whether the task is now marked as done.
     */
    public void printMarkResult(Task task, boolean isDone) {
        System.out.println(DIVIDE);
        if (isDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task.toString());
        System.out.println(DIVIDE);
    }

    /**
     * Prints a divider line.
     */
    public void showLine() {
        System.out.println(DIVIDE);
    }

    /**
     * Prints the welcome message shown when the application starts.
     */
    public void printWelcome() {
        System.out.println(DIVIDE);
        System.out.println(" Welcome to whoru!");
        System.out.println(DIVIDE);
    }

    /**
     * Prints an error message to the user.
     *
     * @param message Error message to be displayed.
     */
    public void printErrorMessage(String message) {
        System.out.print(formatErrorMessage(message));
    }

    /**
     * Reads and returns the next command entered by the user.
     *
     * @return User command after trimming leading and trailing whitespace.
     * @throws EmptyTaskCommandException If the user enters an empty command.
     */
    public String readCommand() throws EmptyTaskCommandException {
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            throw new EmptyTaskCommandException("Start with a non-empty command");
        }
        return line;
    }

    /**
     * Prints a message indicating that no matching task was found.
     */
    public void printNoTask() {
        System.out.println(DIVIDE);
        System.out.println(" No matching task found.");
        System.out.println(DIVIDE);
    }
}