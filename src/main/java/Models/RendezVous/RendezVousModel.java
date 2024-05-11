package Models.RendezVous;

import DataBases.PatientDB;
import DataBases.RendezVouzDB;

import java.io.*;

import static com.example.patient_management_system.HelloApplication.categoryDbFileName;
import static com.example.patient_management_system.HelloApplication.patientsDBFileName;

public class RendezVousModel implements Serializable {


    RendezVouzDB dataBase;

    public RendezVousModel(RendezVouzDB dataBase) {
        this.dataBase = dataBase;
    }

    public RendezVousModel(){}

    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(categoryDbFileName))){
            objectOutputStream.writeObject(dataBase);
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(categoryDbFileName))) {

            dataBase = (RendezVouzDB) objectInputStream.readObject();
            System.out.println("loading the rendez_vous model");
        }
    }




    public void createAtelier(RendezVousSchema rendezVous){
        dataBase.create(rendezVous.date, rendezVous.heure, rendezVous);
    }



}
