package whoru.exception;

/**
 * Represents the error thrown when no description provided for a task
 */
public class EmptyDescriptionException extends WhoruException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
