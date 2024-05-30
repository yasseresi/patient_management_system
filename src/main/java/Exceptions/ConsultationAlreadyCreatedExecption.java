package Exceptions;

public class ConsultationAlreadyCreatedExecption extends Exception{

    @Override
    public String getMessage() {
        return "Consultation déjà créée, vous devez choisir un rendez-vous de suivi.";
    }
}
