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

    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.usersDirectoryName + "/" +  HelloApplication.currentPatientName + "/"+ HelloApplication.categoryDbFileName))) {
            objectOutputStream.writeObject(dataBase);
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName + "/" +  HelloApplication.currentPatientName + "/"+ HelloApplication.categoryDbFileName))) {

            dataBase = (RendezVouzDB) objectInputStream.readObject();
            System.out.println("loading the rendez_vous model");
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


}
