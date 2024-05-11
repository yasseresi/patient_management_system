package Models.RendezVous;

import Models.Patient.PatientSchema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AtelierSchema extends RendezVousSchema{

    private String thematique;
    private List<PatientSchema> participants;

    public AtelierSchema(LocalDate date , LocalTime heure,String thematique, List<PatientSchema> participants) {
        this.thematique = thematique;
        this.participants = participants;
        this.date = date;
        this.heure= heure;
    }


    public String getThematique() {
        return thematique;
    }

    public void setThematique(String thematique) {
        this.thematique = thematique;
    }

    public List<PatientSchema> getParticipants() {
        return participants;
    }

    public void setParticipants(List<PatientSchema> participants) {
        this.participants = participants;
    }
}
