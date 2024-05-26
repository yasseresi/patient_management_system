package Models.Objectif;

import DataBases.ObjectifDB;
import DataBases.RendezVouzDB;
import DataBases.objectifFileDB;
import Models.RendezVous.RendezVousSchema;
import com.example.patient_management_system.HelloApplication;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class ObjectifModel {

    ObjectifDB dataBase;

    public ObjectifModel(objectifFileDB dataBase) {
        this.dataBase = dataBase;
    }

    public ObjectifModel(){
    }

    public void load() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName + "/"+ HelloApplication.fichSuivisDBFileName))) {
            // Attempt to read the object from the file
            dataBase = (ObjectifDB) objectInputStream.readObject();
            System.out.println("Loading the objectif model");
        } catch (EOFException e) {
            // Handle the case where the file is empty
            System.out.println("The objectif data file is empty.");
        } catch (IOException | ClassNotFoundException e) {
            // Handle any other IOException or ClassNotFoundException that might occur
            e.printStackTrace();
        }
    }

    public ArrayList<ObjectifShema> findComplete(){
        return dataBase.findComplete();
    }

}
