package Exceptions;

public class PatientAllreadyExistException extends Exception {
    @Override
    public String getMessage() {
        return "Patient already exists";
    }
}
