package Exceptions;

public class UserDoesNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "Orthophoniste n'existe pas";
    }
}

