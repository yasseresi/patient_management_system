package Exceptions;

public class ConsultationAlreadyPassedExecption extends Exception {
    @Override
    public String getMessage() {
    return "Consultation already passed";
}
}
