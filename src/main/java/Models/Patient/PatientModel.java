package Models.Patient;

import DataBases.PatientDB;
import Exceptions.PatientAlreadyExistException;
import Exceptions.PatientDoesNotExistException;
import Exceptions.UniqueUsernameViolationException;
import Exceptions.UserDoesNotExistException;
import Models.DossierPatient.DossierPatientSchema;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.ObservableList;

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
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.usersDirectoryName+"/"+HelloApplication.currentUserName + "/" + patientsDBFileName))){
            objectOutputStream.writeObject(dataBase);
            dataBase.clear();
        }
    }

    public void load() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName+"/"+HelloApplication.currentUserName + "/" + patientsDBFileName))) {
            dataBase = (PatientDB) objectInputStream.readObject();
            System.out.println("loading the patient model");
        } catch (EOFException e) {
            System.out.println("Error while loading Patient model: Unexpected end of file. The database file may be corrupted or incomplete.");
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    public boolean exists(String nom,String prenom){
        return this.dataBase.exists(nom,prenom);
    }

    public PatientSchema create(PatientSchema userSchema) throws  PatientAlreadyExistException {
            return this.dataBase.create(userSchema);

    }

    public void create(String nom, String prenom, LocalDate dateNaissance, String lieuNaissance, String adresse, int nbTelephone, boolean nouveau)  {
        this.dataBase.create(nom,prenom,caluclateAge(dateNaissance),dateNaissance,lieuNaissance,adresse,nbTelephone,nouveau);
    }

    public PatientSchema find(String nom,String prenom)  {
            return this.dataBase.find(nom,prenom);

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

    public boolean isEmpty(){
        return this.dataBase.isEmpty();
    }

    private int caluclateAge(LocalDate dateNaissance){
        return LocalDate.now().getYear() - dateNaissance.getYear();
    }

    public ObservableList<PatientSchema> getPatients(){
        return this.dataBase.getPatients();
    }

    public PatientSchema getPatientById(String PatientName){
        return this.dataBase.getPatientById(PatientName);
    }

}
