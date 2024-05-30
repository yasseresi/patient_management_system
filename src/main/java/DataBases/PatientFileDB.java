package DataBases;

import Exceptions.PatientAlreadyExistException;
import Exceptions.PatientDoesNotExistException;
import Models.DossierPatient.DossierPatientSchema;
import Models.Patient.AdultSchema;
import Models.Patient.EnfantSchema;
import Models.Patient.PatientSchema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class PatientFileDB implements PatientDB, Serializable {

    private ArrayList<PatientSchema> patients = new ArrayList<>();

    public PatientFileDB(ArrayList< PatientSchema> patients) {
        this.patients = patients;
    }

    public PatientFileDB() {
    }

    @Override
    public void create(String nom, String prenom, int age, LocalDate dateNaissance, String lieuNaissance, String adresse,int nbTelephone, boolean nouveau)  {
        //creating an object of type PatientSchema
        System.out.println("----avant patient creation");
        PatientSchema patient = (age <= 13) ? patient = new EnfantSchema(nom, prenom, age, dateNaissance, lieuNaissance, adresse,nbTelephone, nouveau) : new AdultSchema(nom, prenom, age, dateNaissance, lieuNaissance, adresse,nbTelephone, nouveau);
        System.out.println("----after patient creation : " + patient.toString());
        System.out.println("after check of existance in the patient db");
        patients.add(patient);
        System.out.println("the db size is :" +patients.size());
        System.out.println("\n");
        for (PatientSchema patientt : patients){
            System.out.println( patientt.toString());
        }
        System.out.println("Patient added successfully : "+patient.getNom() + " " + patient.getPrenom());

    }



    @Override
    public PatientSchema create(PatientSchema newPatient) throws PatientAlreadyExistException {
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
    public PatientSchema find(String nom, String prenom)  {
        for (PatientSchema patient : patients) {
            if (patient.getNom().equals(nom) && patient.getPrenom().equals(prenom)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public PatientSchema update(String nom, String prenom, PatientSchema patientSchema) throws PatientDoesNotExistException , PatientAlreadyExistException {
        //the trow statement will be executed if the patient does not exist while executing the find method
        PatientSchema oldPatient = find(nom, prenom);
        patients.remove(oldPatient);
        if (!oldPatient.equals(patientSchema)) patients.add(patientSchema);
        else  throw new PatientAlreadyExistException();
        return patientSchema;
    }

    @Override
    public PatientSchema update(PatientSchema patientSchema) throws PatientAlreadyExistException, PatientDoesNotExistException{
        return update(patientSchema.getNom(), patientSchema.getPrenom(), patientSchema);
    }
    public boolean isEmpty(){
        return patients.isEmpty();
    }

    public ObservableList<PatientSchema> getPatients(){
        //todo: change the type to normal TreeSet and then change it to observable Set in the controller
        return FXCollections.observableArrayList(patients);
    }

    public PatientSchema getPatientById(String PatientName) {
        for (PatientSchema patient : patients ) {
            String id = patient.getNom() + " " + patient.getPrenom();
            if (id.equals(PatientName)) {
                return patient;
            }
        }
        return null; // Return null if no matching Patien Names is found
    }

    public void clear(){
        patients.clear();
    }
}
