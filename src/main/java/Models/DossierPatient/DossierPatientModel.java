package Models.DossierPatient;

import Exceptions.ConsultationAlreadyPassedExecption;
import Exceptions.ConsultationFirstException;
import Models.Patient.PatientSchema;
import Models.RendezVous.RendezVousSchema;
import com.example.patient_management_system.HelloApplication;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

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


    public boolean existe(String nom, String prenom){
        Iterator<DossierPatientSchema> iterator = dossierPatients.iterator();
        DossierPatientSchema dossier = new DossierPatientSchema("");
        while (iterator.hasNext()){
            if (dossier.getId() == nom + " " + prenom) return true;

        }
        return false;
    }


    public DossierPatientSchema getDossierPatientSelonID(String nom,String prenom){
        Iterator<DossierPatientSchema> iterator = dossierPatients.iterator();
        DossierPatientSchema dossier = null;
        while (iterator.hasNext()){
            if (dossier.getId() == nom + " " + prenom) return dossier;

        }
        return null;
    }

    public void updateDossierPatient(DossierPatientSchema dossierPatientSchema){
        for (DossierPatientSchema dossier : dossierPatients){
            if (dossier.getId().equals(dossierPatientSchema.getId())){
                dossier = dossierPatientSchema;
            }
        }
    }


    //this function add a medicalFolder when creating a new rendezVous so if the patient new doesn't have any one we create one and add the rendezVous
    public void CreerRendezVous(RendezVousSchema rendezVousSchema , PatientSchema patientSchema) throws ConsultationFirstException, ConsultationAlreadyPassedExecption {
        if (existe(patientSchema.getNom(), patientSchema.getPrenom())) {
            DossierPatientSchema dossier = getDossierPatientSelonID(patientSchema.getNom(), patientSchema.getPrenom());
            dossier.getRendezVous().createRendezVous(rendezVousSchema);
            updateDossierPatient(dossier);
        }else{
            String id = patientSchema.getNom()+" "+patientSchema.getPrenom();
            DossierPatientSchema dossier = new DossierPatientSchema(id);
            dossier.getRendezVous().createRendezVous(rendezVousSchema);
            dossierPatients.add(dossier);
        }
    }


    public ArrayList<RendezVousSchema> getRendezVousOfToday(){
        LocalDate currentDay = LocalDate.now();
        ArrayList<RendezVousSchema> rendezVous =  new ArrayList<>();
        for (DossierPatientSchema dossier : dossierPatients){
            rendezVous.addAll(dossier.getRendezVous().findAll(currentDay));
        }
        return rendezVous;
    }


    public String toString() {
        for (DossierPatientSchema dossierPatientSchema : dossierPatients) {

            System.out.println("patient : "+ dossierPatientSchema.getId());

        }
        return null;
    }


    //    public DossierPatientSchema getDossierPatient() {
//        return dossierPatients;
//    }

//    public void setDossierPatient(DossierPatientSchema dossierPatient) {
//        this.dossierPatient = dossierPatient;
//    }
}
