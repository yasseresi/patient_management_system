package Exceptions;

public class WrongPasswordException extends Throwable {

    @Override
    public String getMessage(){
        return " Mot de passe incorrect";
    }
}
