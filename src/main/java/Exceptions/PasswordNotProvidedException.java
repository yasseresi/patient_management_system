package Exceptions;

public class PasswordNotProvidedException extends Throwable {
    @Override
    public String getMessage(){
        return "Mot de passe non fourni";
    }
}
