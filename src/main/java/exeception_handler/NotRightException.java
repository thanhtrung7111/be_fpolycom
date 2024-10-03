package exeception_handler;

public class NotRightException extends RuntimeException {
    public NotRightException(String message) {
        super(message);
    }
}
