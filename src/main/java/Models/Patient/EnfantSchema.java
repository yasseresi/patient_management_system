package Models.Patient;

import java.time.LocalDate;
import java.util.Locale;

public class EnfantSchema extends PatientSchema{


    public EnfantSchema(String nom, String prenom, int age, LocalDate dateNaissance, String lieuNaissance, String adresse,int nbTelephonel ,boolean nouveau) {
        super(nom, prenom, age, dateNaissance, lieuNaissance, adresse,nbTelephonel, nouveau);
    }

    public EnfantSchema(String nom, String prenom , int age){
        super(nom,prenom,age);
    }




}
