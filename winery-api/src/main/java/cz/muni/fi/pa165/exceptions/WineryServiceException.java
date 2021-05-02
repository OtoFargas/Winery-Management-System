package cz.muni.fi.pa165.exceptions;

public class WineryServiceException extends RuntimeException {
    public WineryServiceException() {
        super();
    }

    public WineryServiceException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WineryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WineryServiceException(String message) {
        super(message);
    }

    public WineryServiceException(Throwable cause) {
        super(cause);
    }
}
