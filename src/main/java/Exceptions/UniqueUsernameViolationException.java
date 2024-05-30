package Exceptions;

public class UniqueUsernameViolationException extends Exception {

    @Override
    public String getMessage() {
        return "Username déjà existant";
    }
}
