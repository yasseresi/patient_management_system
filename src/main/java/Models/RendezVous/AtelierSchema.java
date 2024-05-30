package Models.RendezVous;

import Models.Patient.PatientSchema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AtelierSchema extends RendezVousSchema{

    private String thematique;
    private List<String> participants;

    public AtelierSchema(LocalDate date , LocalTime heure,String thematique, List<String> participants) {
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

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "AtelierSchema{" +
                "thematique='" + thematique + '\'' +
                ", participants=" + participants +
                ", date=" + date +
                ", heure=" + heure +
                ", duree='" + duree + '\'' +
                '}';
    }
}
