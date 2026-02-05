import java.util.Scanner;

public class Whoru {
    private static final int MAX_TASKS = 100;
    private static final String DIVIDE = "____________________________________________________________";

    private final Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public static void main(String[] args) {
        System.out.println(DIVIDE);
        System.out.println(" Hello! I'm Whoru");
        System.out.println(" What can I do for you?");
        System.out.println(DIVIDE);

        Whoru whoru = new Whoru();
        whoru.listenForCommands();
    }

    private void listenForCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("bye")) {
                printBye();
                break;
            } else if (line.equalsIgnoreCase("list")) {
                printList();
            } else if (line.startsWith("mark")) {
                handleMarkCommand(line, true);
            } else if (line.startsWith("unmark")) {
                handleMarkCommand(line, false);
            } else if (line.startsWith("todo")) {
                addTodo(line);
            } else if (line.startsWith("deadline")) {
                addDeadline(line);
            } else if (line.startsWith("event")) {
                addEvent(line);
            }

        }
    }

    private void addTodo(String input) {
        String description = input.substring("todo".length()).trim(); // start from space after todo
        if (description.isEmpty()) {
            System.out.println(DIVIDE);
            System.out.println("empty task description");
            System.out.println(DIVIDE);
            return;
        }

        addTask(new Todo(description));
    }

    private void addDeadline(String input) {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            System.out.println(DIVIDE);
            System.out.println("Missing by time");
            System.out.println(DIVIDE);
            return;
        }

        String description = input.substring("deadline".length(), byIndex).trim();
        String by = input.substring(byIndex + "/by".length()).trim();

        if (description.isEmpty() || by.isEmpty()) {
            System.out.println(DIVIDE);
            System.out.println("empty task description");
            System.out.println(DIVIDE);
            return;
        }

        addTask(new Deadline(description, by));
    }

    private void addEvent(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            System.out.println(DIVIDE);
            System.out.println("Missing from or to time");
            System.out.println(DIVIDE);
            return;
        }

        String description = input.substring("event".length(), fromIndex).trim();
        String from = input.substring(fromIndex + "/from".length(), toIndex).trim();
        String to = input.substring(toIndex + "/to".length()).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println(DIVIDE);
            System.out.println("empty task description");
            System.out.println(DIVIDE);
            return;
        }

        addTask(new Event(description, from, to));
    }

    private void handleMarkCommand(String input, boolean shouldMarkDone) {
        String command = shouldMarkDone ? "mark" : "unmark";
        String numberPart = input.substring(command.length()).trim();

        int taskIndex = extractTaskNumber(numberPart);
        if (taskIndex == -1) {
            System.out.println(DIVIDE);
            System.out.println("Please provide a valid task number.");
            System.out.println(DIVIDE);
            return;
        }

        Task task = tasks[taskIndex - 1]; //zero base index and 1 base index
        task.updateDoneStatus(shouldMarkDone);
        printMarkResult(task, shouldMarkDone);
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
        if (taskCount >= MAX_TASKS) {
            System.out.println(DIVIDE);
            System.out.println("Exceeding max no of tasks: " + MAX_TASKS);
            System.out.println(DIVIDE);
            return;
        }

        tasks[taskCount] = task;
        taskCount++;
        printAddResult(task);
    }

    private void printAddResult(Task task) {
        System.out.println(DIVIDE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(DIVIDE);
    }

    private void printList() {
        System.out.println(DIVIDE);
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
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
