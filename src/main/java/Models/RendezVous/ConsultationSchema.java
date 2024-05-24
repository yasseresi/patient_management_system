package Models.RendezVous;

import Models.Patient.AdultSchema;
import Models.Patient.EnfantSchema;
import Models.Patient.PatientSchema;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultationSchema extends RendezVousSchema implements Serializable {

    private PatientSchema patient;
    //TODO: CHECK LATER THE CORRECT TIME DURATION OF CONSULTATION
    public ConsultationSchema(LocalDate date, LocalTime heure,String nom, String prenom, int age, LocalDate dateNaissance, String lieuNaissance,int nbTelephone, String adresse) {
        this.date = date;
        this.heure = heure;
        this.patient = (age <= 13) ? new EnfantSchema(nom,prenom,age,dateNaissance,lieuNaissance,adresse,nbTelephone,true) : new AdultSchema(nom,prenom,age,dateNaissance,lieuNaissance,adresse,nbTelephone,true);
    }
}
