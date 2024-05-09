package Exceptions;

public class WrongPasswordException extends Throwable {

    @Override
    public String getMessage(){
        return " the password is wrong";
    }
}
