package whoru.exception;

/**
 * Represents the error thrown when there is no task command parsed
 */
public class EmptyTaskCommandException extends WhoruException {
    public EmptyTaskCommandException(String message) {
        super(message);
    }
}
