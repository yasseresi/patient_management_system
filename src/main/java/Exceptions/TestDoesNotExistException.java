package Exceptions;

public class TestDoesNotExistException extends Exception {

    @Override
    public String getMessage() {
        return "Le test n'existe pas.";
    }
}
