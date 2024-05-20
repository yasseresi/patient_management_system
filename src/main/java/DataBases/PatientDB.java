package DataBases;

import Exceptions.PatientAlreadyExistException;
import Exceptions.PatientDoesNotExistException;
import Exceptions.UniqueUsernameViolationException;
import Exceptions.UserDoesNotExistException;
import Models.Patient.PatientSchema;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;

public interface PatientDB extends Serializable {
public void create(String nom, String prenom, int age, LocalDate dateNaissance, String lieuNaissance, String adresse, boolean nouveau) throws PatientAlreadyExistException;
public PatientSchema create(PatientSchema newPatient) throws UniqueUsernameViolationException, PatientAlreadyExistException;
public boolean exists(String nom, String prenom);
public PatientSchema find(String nom, String prenom) throws UserDoesNotExistException, PatientDoesNotExistException, PatientDoesNotExistException;
public PatientSchema update(String nom, String prenom, PatientSchema patientSchema) throws PatientDoesNotExistException, PatientAlreadyExistException;
public PatientSchema update(PatientSchema patientSchema) throws PatientAlreadyExistException, PatientDoesNotExistException;
public boolean isEmpty();
public ObservableList<PatientSchema> getPatients();

}
