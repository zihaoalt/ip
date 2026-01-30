import java.util.Scanner;

public class Whoru {
    public static void main(String[] args) {
        String logo =
                "____________________________________________________________\n"
                        + "Hello! I'm Whoru\n"
                        + "What can I do for you?\n"
                        + "Try typing something\n"
                        + "____________________________________________________________\n";


        System.out.println(logo);

        new Whoru().listenCommand();
    }

    private void listenCommand() {
        Scanner in = new Scanner(System.in);
        String line;
        String outString;

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                outString = "____________________________________________________________\n"
                                + "Byebye hope to see you next time\n"
                                + "____________________________________________________________\n";
                System.out.println(outString);
                break;
            }
            outString = "____________________________________________________________\n"
                            + "Did you just say " + line + "\n"
                            + "____________________________________________________________\n";
            System.out.println(outString);
        }
    }
}

