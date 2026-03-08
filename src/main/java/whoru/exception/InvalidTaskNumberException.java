package whoru.exception;

/**
 * Represents the error thrown when the task index is invalid
 */
public class InvalidTaskNumberException extends WhoruException {
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
