package whoru.utils;

/**
 * A helper utility class for formating error message
 */
public class formatter {
    public static String DIVIDE = "____________________________________________________________\n";

    /**
     * Formats the error message with uniformed padding
     * @param errorMessage the error message to be padded
     * @return the formated message
     */
    public static String formatErrorMessage(String errorMessage) {
        return DIVIDE + errorMessage + "\n" + DIVIDE;
    }
}
