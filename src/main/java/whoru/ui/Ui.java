package whoru.ui;

import whoru.exception.EmptyTaskCommandException;
import whoru.exception.WhoruException;
import whoru.task.Task;
import whoru.tasklist.TaskList;

import java.util.Scanner;

import static whoru.utils.formatter.formatErrorMessage;

public class Ui {
    private static final String DIVIDE = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printDeleteResult(Task task) {
        System.out.println(DIVIDE);
        System.out.println(" OK, I've deleted this task.");
        System.out.println("   " + task.toString());
        System.out.println(DIVIDE);
    }

    public void handleUnknownCommand() {
        System.out.println(DIVIDE);
        System.out.println("Sry unknown command detected, try commands like todo/deadline");
        System.out.println(DIVIDE);
        return;
    }

    public void printAddResult(Task task, int size) {
        System.out.println(DIVIDE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println(DIVIDE);
    }

    public void printBye() {
        System.out.println(DIVIDE);
        System.out.println(" ByeBye !");
        System.out.println(DIVIDE);
    }

    public void printList(TaskList tasks) throws WhoruException {
        System.out.println(DIVIDE);
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }

        System.out.println(DIVIDE);
    }

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

    public void showLine() {
        System.out.println(DIVIDE);
    }

    public void printWelcome() {
        System.out.println(DIVIDE);
        System.out.println(" Welcome to whoru!");
        System.out.println(DIVIDE);
    }

    public void printErrorMessage(String message) {
        System.out.print(formatErrorMessage(message));
    }

    public String readCommand() throws EmptyTaskCommandException {
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            throw new EmptyTaskCommandException("Start with a non-empty command");
        }
        return line;
    }
}
