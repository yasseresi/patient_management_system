package Exceptions;

public class UniqueUsernameViolationException extends Exception {

    @Override
    public String getMessage() {
        return "Username already exists !";
    }
}
