package Models.RendezVous;

import DataBases.RendezVouzDB;
import Exceptions.ConsultationAlreadyPassedExecption;
import Exceptions.ConsultationFirstException;
import com.example.patient_management_system.HelloApplication;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static com.example.patient_management_system.HelloApplication.categoryDbFileName;

public class RendezVousModel implements Serializable {


    RendezVouzDB dataBase;

    public RendezVousModel(RendezVouzDB dataBase) {
        this.dataBase = dataBase;
    }

    public RendezVousModel() {
    }

    public void load() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName + "/"+ HelloApplication.categoryDbFileName))) {
            // Attempt to read the object from the file
            dataBase = (RendezVouzDB) objectInputStream.readObject();
            System.out.println("Loading the rendez_vous model");
        } catch (EOFException e) {
            // Handle the case where the file is empty
            System.out.println("The rendez_vous data file is empty.");
        } catch (IOException | ClassNotFoundException e) {
            // Handle any other IOException or ClassNotFoundException that might occur
            e.printStackTrace();
        }
    }


    public void createAtelier(RendezVousSchema rendezVous) throws ConsultationFirstException, ConsultationAlreadyPassedExecption {
        dataBase.create(rendezVous.date, rendezVous.heure, rendezVous);
    }

    public ArrayList<RendezVousSchema> findAll(LocalDate date) {
        return dataBase.findAll(date);
    }


    public RendezVousSchema find(LocalDate date, LocalTime time) {
        return dataBase.find(date, time);
    }

    public ArrayList<RendezVousSchema> findAll(){
        return dataBase.findAll();
    };

}
