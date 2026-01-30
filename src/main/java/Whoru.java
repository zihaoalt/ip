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
            } else if (line.trim().contains("mark")) {
                int taskNumber = this.extractTaskNumber(line.trim());
                if (taskNumber == -1 || taskNumber > this.taskCount) {
                    outString = "____________________________________________________________\n"
                                    + "     Provide a valid task number to continue\n"
                                    + "____________________________________________________________\n";
                    System.out.println(outString);
                    continue;
                }

                Task task = tasks[taskNumber - 1];
                task.updateDoneStatus(line.trim().contains("unmark") ? false : true);
                String middleLine = line.trim().contains("unmark") ? "OK, I've marked this task as not done yet: \n" : "Nice! I've marked this task as done:\n";
                outString = "____________________________________________________________\n"
                                + "     " + middleLine
                                + "     " + task.getPrintingString() + "\n"
                                + "____________________________________________________________\n";
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

