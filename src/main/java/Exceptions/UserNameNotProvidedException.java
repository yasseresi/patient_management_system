package Exceptions;

public class UserNameNotProvidedException extends Throwable {
    @Override
    public String getMessage() {
        return "Nom d'utilisateur non fourni";
    }

}
