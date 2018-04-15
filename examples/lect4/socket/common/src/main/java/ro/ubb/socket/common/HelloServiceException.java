package ro.ubb.socket.common;

public class HelloServiceException extends RuntimeException {
    public HelloServiceException(String message) {
        super(message);
    }

    public HelloServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public HelloServiceException(Throwable cause) {
        super(cause);
    }
}
