package DataBases;

import Exceptions.PatientAllreadyExistException;
import Exceptions.PatientDoesNotExistException;
import Exceptions.UniqueUsernameViolationException;
import Exceptions.UserDoesNotExistException;
import Models.Patient.AdultSchema;
import Models.Patient.EnfantSchema;
import Models.Patient.PatientSchema;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class PatientFileDB implements PatientDB, Serializable {
TreeSet<PatientSchema> patients = new TreeSet<>();

    public PatientFileDB(TreeSet< PatientSchema> patients) {
        this.patients = patients;
    }

    public PatientFileDB() {
    }

    @Override
    public void create(String nom, String prenom, int age, String dateNaissance, String lieuNaissance, String adresse, boolean nouveau) throws UniqueUsernameViolationException {
        //creating an object of type PatientSchema
        PatientSchema patient = (age <= 13) ? patient = new EnfantSchema(nom, prenom, age, dateNaissance, lieuNaissance, adresse, nouveau) : new AdultSchema(nom, prenom, age, dateNaissance, lieuNaissance, adresse, nouveau);

        if (patients.contains(patient)) throw new UniqueUsernameViolationException();
        patients.add(patient);
        System.out.println("Patient added successfully : "+patient.getNom() + " " + patient.getPrenom());

    }

    @Override
    public PatientSchema create(PatientSchema newPatient) throws PatientAllreadyExistException {
        if (patients.contains(newPatient)) throw new PatientAllreadyExistException();
        patients.add(newPatient);
        return newPatient;
    }

    @Override
    public boolean exists(String nom, String prenom) {
        Iterator<PatientSchema> it = patients.iterator();
        //TODO: CHECK IF THE LOOP WORKS FINE
        while (it.hasNext()) {
            PatientSchema patient = it.next();
            if (patient.getNom().equals(nom) && patient.getPrenom().equals(prenom)) return true;
        }
        return false;
    }



    @Override
    public PatientSchema find(String nom, String prenom) throws PatientDoesNotExistException {
        for (PatientSchema patient : patients) {
            if (patient.getNom().equals(nom) && patient.getPrenom().equals(prenom)) {
                return patient;
            }
        }
        throw new PatientDoesNotExistException();
    }

    @Override
    public PatientSchema update(String nom, String prenom, PatientSchema patientSchema) throws PatientDoesNotExistException , PatientAllreadyExistException{
        //the trow statement will be executed if the patient does not exist while executing the find method
        PatientSchema oldPatient = find(nom, prenom);
        patients.remove(oldPatient);
        if (!oldPatient.equals(patientSchema)) patients.add(patientSchema);
        else  throw new PatientAllreadyExistException();
        return patientSchema;
    }

    @Override
    public PatientSchema update(PatientSchema patientSchema) throws PatientAllreadyExistException , PatientDoesNotExistException{
        return update(patientSchema.getNom(), patientSchema.getPrenom(), patientSchema);
    }
}
