package tel.bvm.algoritmLectionThree.exception;

public class NullPointerItemException extends RuntimeException {
    public NullPointerItemException() {
    }

    public NullPointerItemException(String message) {
        super(message);
    }

    public NullPointerItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullPointerItemException(Throwable cause) {
        super(cause);
    }

    public NullPointerItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
