package seedu.address.model.tutorial.exceptions;

public class InvalidTutorialException extends RuntimeException {
    public InvalidTutorialException() {
        super("Matching tutorial cannot be found.");
    }
}
