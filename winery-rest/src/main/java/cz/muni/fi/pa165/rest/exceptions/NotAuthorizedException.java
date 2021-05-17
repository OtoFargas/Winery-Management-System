package cz.muni.fi.pa165.rest.exceptions;

/**
 * @author Oto Fargas
 */
public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String message) {
        super(message);
    }

}
