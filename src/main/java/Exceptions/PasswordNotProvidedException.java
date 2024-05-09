package Exceptions;

public class PasswordNotProvidedException extends Throwable {
    @Override
    public String getMessage(){
        return "Password not provided";
    }
}
