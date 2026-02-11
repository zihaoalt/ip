package whoru.utils;

public class formatter {
    public static String DIVIDE = "____________________________________________________________\n";

    public static String formatErrorMessage(String errorMessage) {
        return DIVIDE + errorMessage + "\n" + DIVIDE;
    }
}
