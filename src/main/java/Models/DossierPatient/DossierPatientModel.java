package Models.DossierPatient;

import Exceptions.ConsultationAlreadyCreatedExecption;
import Exceptions.ConsultationFirstException;
import Models.Diagnostique.TroubleModel;
import Models.Diagnostique.TypeTrouble;
import Models.Patient.PatientSchema;
import Models.RendezVous.AtelierSchema;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.RendezVousSchema;
import Models.RendezVous.SuiviSchema;
import com.example.patient_management_system.HelloApplication;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class DossierPatientModel implements Serializable{

    private ArrayList<DossierPatientSchema> dossierPatients;



    public DossierPatientModel(ArrayList<DossierPatientSchema> dossierPatients) {
        this.dossierPatients = dossierPatients;
    }

    public DossierPatientModel() {
        this.dossierPatients = new ArrayList<>();
    }

    public ArrayList<DossierPatientSchema> getDossierPatients() {
        return dossierPatients;
    }

    public void setDossierPatients(ArrayList<DossierPatientSchema> dossierPatients) {
        this.dossierPatients = dossierPatients;
    }

    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.dossierPatientFileName))) {
            objectOutputStream.writeObject(dossierPatients);
            dossierPatients.clear();
        }
    }

    public void load() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.dossierPatientFileName))) {
            dossierPatients = (ArrayList<DossierPatientSchema>) objectInputStream.readObject();
            System.out.println("loading the patientFolder model");
        } catch (EOFException e) {
            System.out.println("Error while loading paitentFolder model: Unexpected end of file. The database file may be corrupted or incomplete.");
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    public void creerDossierPatient(PatientSchema patient) {

        String id = patient.getNom() + " " + patient.getPrenom();

        DossierPatientSchema dossierPatientSchema = new DossierPatientSchema(id);
        System.out.println("the id of the created dossier is :" + dossierPatientSchema.toString());
        dossierPatients.add(dossierPatientSchema);

    }




    public boolean existe(String nom, String prenom) {
        System.out.println("size" + dossierPatients.size());
        for (DossierPatientSchema dossier : dossierPatients) {
            if (dossier.getId().equals(nom + " " + prenom)) return true;
        }
        return false;

    }


    public DossierPatientSchema getDossierPatientSelonID(String nom, String prenom) {
        Iterator<DossierPatientSchema> iterator = dossierPatients.iterator();
        DossierPatientSchema dossier = new DossierPatientSchema("");
        while (iterator.hasNext()) {
            dossier = iterator.next();
            if (Objects.equals(dossier.getId(), nom + " " + prenom)) return dossier;

        }
        return null;
    }

    public DossierPatientSchema getDossierPatientSelonID(String patientName) {
        Iterator<DossierPatientSchema> iterator = dossierPatients.iterator();
        DossierPatientSchema dossier = new DossierPatientSchema("");
        while (iterator.hasNext()) {
            dossier = iterator.next();
            if (Objects.equals(dossier.getId(), patientName)) return dossier;

        }
        return null;
    }




    public void updateDossierPatient(DossierPatientSchema dossierPatientSchema) {
        for (DossierPatientSchema dossier : dossierPatients) {
            if (dossier.getId().equals(dossierPatientSchema.getId())) {
                dossier = dossierPatientSchema;
            }
        }
    }



    public void CreerAtelier(AtelierSchema atelier){
        for (String username : atelier.getParticipants()){
            DossierPatientSchema dossierPatientSchema = getDossierPatientSelonID(username);
            dossierPatientSchema.getRendezVous().createAtelier(atelier);
            updateDossierPatient(dossierPatientSchema);
        }
    }

    public void CreerConsultation(ConsultationSchema consultation, PatientSchema patient) throws ConsultationAlreadyCreatedExecption {
        DossierPatientSchema dossier = getDossierPatientSelonID(patient.getNom(), patient.getPrenom());
        System.out.println(dossier.toString());

        dossier.getRendezVous().createConsultation(consultation);
        updateDossierPatient(dossier);

    }

    public void CreerSuivi(SuiviSchema suivi, PatientSchema patient) throws ConsultationFirstException {
        DossierPatientSchema dossier = getDossierPatientSelonID(patient.getNom(), patient.getPrenom());
        System.out.println(dossier.toString());

        dossier.getRendezVous().createSuivi(suivi);
        updateDossierPatient(dossier);

    }


    public ArrayList<RendezVousSchema> getRendezVousOfDay(LocalDate date) {
        ArrayList<RendezVousSchema> rendezVous = new ArrayList<>();
        for (DossierPatientSchema dossier : dossierPatients) {
            rendezVous.addAll(dossier.getRendezVous().findAll(date));
        }
        // TODO 0 : SORTE THIS ARRAYLISTBEFORE DESPLAY IT
        rendezVous.sort(new Comparator<RendezVousSchema>() {
            @Override
            public int compare(RendezVousSchema r1, RendezVousSchema r2) {
                int dateComparison = r1.getDate().compareTo(r2.getDate());
                if (dateComparison != 0) {
                    return dateComparison;
                }
                return r1.getHeure().compareTo(r2.getHeure());
            }
        });


        return rendezVous;
    }

    public int getNBPatientParTrouble(TypeTrouble typeTrouble){
        int count =0;
        for (DossierPatientSchema dossierPatientSchema : dossierPatients){
            count+= dossierPatientSchema.getNbTroubleType(typeTrouble);
        }
        return count;
    }


    public String toString() {
        for (DossierPatientSchema dossierPatientSchema : dossierPatients) {

            System.out.println("patient : " + dossierPatientSchema.getId());

        }
        return null;
    }


}
