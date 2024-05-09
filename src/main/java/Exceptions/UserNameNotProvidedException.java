package Exceptions;

public class UserNameNotProvidedException extends Throwable {
    @Override
    public String getMessage() {
        return "Username not provided";
    }

}
