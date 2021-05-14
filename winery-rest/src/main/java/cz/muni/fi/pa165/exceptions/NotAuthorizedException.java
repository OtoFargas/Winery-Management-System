package cz.muni.fi.pa165.exceptions;

/**
 * @author Oto Fargas
 */
public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String message) {
        super(message);
    }

}
