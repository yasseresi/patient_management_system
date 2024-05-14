package Exceptions;

public class ObjectifDoesNotExiste extends Exception{
    @Override
    public String getMessage(){
        return "Objective Does Not Existe";
    }
}
