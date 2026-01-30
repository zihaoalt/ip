import java.util.Scanner;

public class Whoru {
    private final Task[] tasks = new Task[100];
    private int taskCount = 0;
    public static void main(String[] args) {
        String LOGO =
                "____________________________________________________________\n"
                        + "Hello! I'm Whoru\n"
                        + "What can I do for you?\n"
                        + "Try typing something\n"
                        + "____________________________________________________________\n";


        System.out.println(LOGO);

        new Whoru().listenCommand();
    }

    private void listenCommand() {
        Scanner in = new Scanner(System.in);
        String line;
        String outString;

        while (true) {
            line = in.nextLine();
            if ("bye".equals(line.trim())) {
                outString = "____________________________________________________________\n"
                                + "Byebye hope to see you next time\n"
                                + "____________________________________________________________\n";
                System.out.println(outString);
                break;
            } else if ("list".equals(line.trim())) {
                outString = "____________________________________________________________\n";
                int count = 1;
                for (Task task : tasks) {
                    if (taskCount == 0) {
                        outString = outString + "No task for now good, go relax\n";
                    }
                    if (task == null) {
                        break;
                    }
                    outString = outString + Integer.toString(count) + task.getPrintingString() + "\n";
                    count++;
                }
                outString = outString + "____________________________________________________________\n";
                System.out.println(outString);
            } else {
                String taskContent = line.trim();
                Task task = new Task(taskContent);
                this.tasks[taskCount] = task; // add new task
                this.taskCount++;

                outString = "____________________________________________________________\n"
                                + "     added: " + task.getDescription() + "\n"
                                + "____________________________________________________________\n";
                System.out.println(outString);
            }
        }
    }
}

