package DataBases;

import Exceptions.PatientAllreadyExistException;
import Exceptions.PatientDoesNotExistException;
import Exceptions.UniqueUsernameViolationException;
import Exceptions.UserDoesNotExistException;
import Models.Patient.PatientSchema;

import java.io.Serializable;

public interface PatientDB extends Serializable {
public void create(String nom, String prenom, int age, String dateNaissance, String lieuNaissance, String adresse, boolean nouveau) throws UniqueUsernameViolationException;
public PatientSchema create(PatientSchema newPatient) throws UniqueUsernameViolationException, PatientAllreadyExistException;
public boolean exists(String nom, String prenom);
public PatientSchema find(String nom, String prenom) throws UserDoesNotExistException, PatientDoesNotExistException, PatientDoesNotExistException;
public PatientSchema update(String nom, String prenom, PatientSchema patientSchema) throws PatientDoesNotExistException, PatientAllreadyExistException;
public PatientSchema update(PatientSchema patientSchema) throws PatientAllreadyExistException, PatientDoesNotExistException;

}
