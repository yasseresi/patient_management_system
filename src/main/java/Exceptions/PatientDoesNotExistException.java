package Exceptions;

public class PatientDoesNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "Patient does not exist";
    }
}
