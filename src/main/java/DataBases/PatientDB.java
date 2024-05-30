package DataBases;

import Exceptions.PatientAlreadyExistException;
import Exceptions.PatientDoesNotExistException;
import Exceptions.UniqueUsernameViolationException;
import Models.Patient.PatientSchema;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;

public interface PatientDB extends Serializable {
public void create(String nom, String prenom, int age, LocalDate dateNaissance, String lieuNaissance, String adresse,int nbTelephone, boolean nouveau) ;
public PatientSchema create(PatientSchema newPatient) throws PatientAlreadyExistException;
public boolean exists(String nom, String prenom);
public PatientSchema find(String nom, String prenom) ;
public PatientSchema update(String nom, String prenom, PatientSchema patientSchema) throws PatientDoesNotExistException, PatientAlreadyExistException;
public PatientSchema update(PatientSchema patientSchema) throws PatientAlreadyExistException, PatientDoesNotExistException;
public boolean isEmpty();
public ObservableList<PatientSchema> getPatients();
public PatientSchema getPatientById(String PatientName);
    public void clear();

}
