package Exceptions;

public class PatientAlreadyExistException extends Exception {
    @Override
    public String getMessage() {
        return "Patient already exists";
    }
}