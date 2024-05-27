package Models.RendezVous;

import Models.Patient.AdultSchema;
import Models.Patient.EnfantSchema;
import Models.Patient.PatientSchema;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultationSchema extends RendezVousSchema implements Serializable {

    //TODO: CHECK LATER THE CORRECT TIME DURATION OF CONSULTATION
     static int DUREE = 30;
    public ConsultationSchema(LocalDate date, LocalTime heure, Duration dureeMin) {
        this.date = date;
        this.heure = heure;
        super.duree = Duration.ofMinutes(DUREE);
    }
}
