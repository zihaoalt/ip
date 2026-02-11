package whoru.task.exception;

public class EmptyTaskCommandException extends RuntimeException {
    public EmptyTaskCommandException(String message) {
        super(message);
    }
}
