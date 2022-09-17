package eu.rebase.marsrover.exception;

public class UnsupportedRoverCommandException extends RuntimeException{
    public UnsupportedRoverCommandException(String message) {
        super(message);
    }
}
