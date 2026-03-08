package whoru.exception;

/**
 * Represents the exception thrown when missing {@code by} {@code from} {@code to} time or in wrong format
 */
public class MissingTimeException extends WhoruException {
    public MissingTimeException(String message) {
        super(message);
    }
}
