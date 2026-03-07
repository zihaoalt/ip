package whoru.exception;

/**
 * Represents the parent class of all other customized exceptions to print messages
 */
public class WhoruException extends Exception {
    public WhoruException(String message) {
        super(message);
    }
}
