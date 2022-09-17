package eu.rebase.marsrover.exception;

public class InvalidGridPositionException extends RuntimeException {
    public InvalidGridPositionException(String message) {
        super(message);
    }
}
