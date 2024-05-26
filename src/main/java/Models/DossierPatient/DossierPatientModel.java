package Models.DossierPatient;

import DataBases.AnamneseDB;
import com.example.patient_management_system.HelloApplication;

import java.io.*;
import java.util.ArrayList;

public class DossierPatientModel {

    private ArrayList<DossierPatientSchema> dossierPatients;

    public ArrayList<DossierPatientSchema> getDossierPatients() {
        return dossierPatients;
    }

    public void setDossierPatients(ArrayList<DossierPatientSchema> dossierPatients) {
        this.dossierPatients = dossierPatients;
    }


    public DossierPatientModel(ArrayList<DossierPatientSchema> dossierPatients) {
        this.dossierPatients = dossierPatients;
    }

    public DossierPatientModel(){

    }


    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+HelloApplication.dossierPatientFileName))){
            objectOutputStream.writeObject(dossierPatients);
            dossierPatients.clear();
        }
    }

    public void load() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName+"/"+HelloApplication.currentUserName + "/" + HelloApplication.dossierPatientFileName))) {
            dossierPatients = (ArrayList<DossierPatientSchema>) objectInputStream.readObject();
            System.out.println("loading the patientFolder model");
        } catch (EOFException e) {
            System.out.println("Error while loading paitentFolder model: Unexpected end of file. The database file may be corrupted or incomplete.");
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    public void addDossierPatient(DossierPatientSchema dossierPatient) {
        dossierPatients.add(dossierPatient);
    }

    public DossierPatientSchema getDossierPatientById(String PatientName) {
        for (DossierPatientSchema dossierPatient : dossierPatients) {
            if (dossierPatient.getId().equals(PatientName)) {
                return dossierPatient;
            }
        }
        return null; // Return null if no matching DossierPatientSchema is found
    }
//    public DossierPatientSchema getDossierPatient() {
//        return dossierPatients;
//    }

//    public void setDossierPatient(DossierPatientSchema dossierPatient) {
//        this.dossierPatient = dossierPatient;
//    }
}
