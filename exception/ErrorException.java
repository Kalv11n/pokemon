package exception;

public class ErrorException extends RuntimeException {
    public ErrorException(String message) {
        super(message);
    }
}