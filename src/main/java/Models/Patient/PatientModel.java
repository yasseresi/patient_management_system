package Models.Patient;

import DataBases.PatientDB;
import Exceptions.PatientAlreadyExistException;
import Exceptions.PatientDoesNotExistException;
import Exceptions.UniqueUsernameViolationException;
import Exceptions.UserDoesNotExistException;

import java.io.*;
import java.time.LocalDate;

import static com.example.patient_management_system.HelloApplication.patientsDBFileName;

public class PatientModel implements Serializable {

    PatientDB dataBase;

    public PatientModel(PatientDB dataBase) {
        this.dataBase = dataBase;
    }
    public PatientModel() {
    }



    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(patientsDBFileName))){
            objectOutputStream.writeObject(dataBase);
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(patientsDBFileName))) {

            dataBase = (PatientDB) objectInputStream.readObject();
            System.out.println("loading the user model");
        }
    }

    public boolean exists(String nom,String prenom){
        return this.dataBase.exists(nom,prenom);
    }

    public PatientSchema create(PatientSchema userSchema) {
        try {
            return this.dataBase.create(userSchema);
        } catch (UniqueUsernameViolationException | PatientAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(String nom , String prenom, int age, LocalDate dateNaissance, String lieuNaissance, String adresse, boolean nouveau) {
        try {
            this.dataBase.create(nom,prenom,age,dateNaissance,lieuNaissance,adresse,nouveau);
        } catch (PatientAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    public PatientSchema find(String nom,String prenom) {
        try {
            return this.dataBase.find(nom,prenom);
        } catch (UserDoesNotExistException | PatientDoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    public PatientSchema update(String nom,String prenom ,PatientSchema userSchema) {
        try {
            return this.dataBase.update(nom,prenom, userSchema);
        } catch (PatientDoesNotExistException | PatientAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    public PatientSchema update(PatientSchema userSchema) {
        try {
            return this.dataBase.update(userSchema);
        } catch (PatientAlreadyExistException | PatientDoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }


}
