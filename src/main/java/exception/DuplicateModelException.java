package exception;

public class DuplicateModelException extends Exception {
    public DuplicateModelException() {
    }

    public DuplicateModelException(String message) {
        super(message);
    }

    public DuplicateModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateModelException(Throwable cause) {
        super(cause);
    }

    public DuplicateModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
