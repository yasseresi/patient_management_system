package Models.DossierPatient;

import DataBases.AnamneseDB;
import com.example.patient_management_system.HelloApplication;

import java.io.*;

public class DossierPatientModel {

    private DossierPatientSchema dossierPatient;


    public DossierPatientModel(DossierPatientSchema dossierPatient) {
        this.dossierPatient = dossierPatient;
    }

    public DossierPatientModel(){

    }


    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+HelloApplication.currentPatientName +"/"+HelloApplication.patientFolderName))){
            objectOutputStream.writeObject(dossierPatient);
        }
    }

    public void load() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName+"/"+HelloApplication.currentUserName + "/" + HelloApplication.anamneseDBFileName))) {
            dossierPatient = (DossierPatientSchema) objectInputStream.readObject();
            System.out.println("loading the patientFolder   model");
        } catch (EOFException e) {
            System.out.println("Error while loading paitentFolder model: Unexpected end of file. The database file may be corrupted or incomplete.");
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }



}
