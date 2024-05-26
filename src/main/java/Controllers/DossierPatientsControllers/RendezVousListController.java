package Controllers.DossierPatientsControllers;

import DataBases.RendezVousFileDB;
import Exceptions.ConsultationAlreadyPassedExecption;
import Exceptions.ConsultationFirstException;
import Models.DossierPatient.DossierPatientSchema;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.DeroulementSuivi;
import Models.RendezVous.RendezVousSchema;
import Models.RendezVous.SuiviSchema;
import Models.Patient.PatientSchema;
import Utils.SceneSwitcher;
import com.example.patient_management_system.HelloApplication;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class RendezVousListController {

    @FXML
    private Label patientNameLabel;

    @FXML
    private TableView<RendezVousSchema> rendezVousTableView;

    @FXML
    private TableColumn<RendezVousSchema, LocalDate> dateColumn;

    @FXML
    private TableColumn<RendezVousSchema, String> heureColumn;

    @FXML
    private TableColumn<RendezVousSchema, String> dureeColumn;

    @FXML
    private TableColumn<RendezVousSchema, String> typeColumn;

    @FXML
    private TableColumn<RendezVousSchema, String> observationColumn;

    public void setPatientDetails(PatientSchema patient) {
        patientNameLabel.setText(patient.getNom().toUpperCase() + " " + patient.getPrenom().toUpperCase());

        System.out.println("Current Patient: " + HelloApplication.currentPatientName);

        ArrayList<RendezVousSchema> RVs = new ArrayList<>();

        ArrayList<DossierPatientSchema> dossiers = new ArrayList<>();
        dossiers = HelloApplication.dossierPatientModel.getDossierPatients();

        for (DossierPatientSchema dossier : dossiers) {
            if (dossier.getId().equals(HelloApplication.currentPatientName)) {
                RVs = dossier.getRendezVous().findAll();
                System.out.println("id : " + dossier.getId().toUpperCase());
            }
        }

        if (RVs == null) {
            System.out.println("Rendez-vous list is null");
            RVs = new ArrayList<>(); // Initialize an empty list if null
        }

        // Ajouter des rendez-vous d'exemple pour tester
//        try {
//            HelloApplication.rendezvousModel.createAtelier(new ConsultationSchema(LocalDate.of(2023, 5, 25), LocalTime.of(10, 30), "Observation 1", "Docteur A", 1));
//            HelloApplication.rendezvousModel.createAtelier(new ConsultationSchema(LocalDate.of(2023, 6, 10), LocalTime.of(11, 0), "Observation 2", "Docteur B", 2));
//            HelloApplication.rendezvousModel.createAtelier(new SuiviSchema(LocalDate.of(2023, 7, 15), LocalTime.of(9, 45), DeroulementSuivi.EN_LIGNE,1 ));
//        } catch (ConsultationAlreadyPassedExecption | ConsultationFirstException e) {
//            e.printStackTrace();
//        }

        rendezVousTableView.setItems(FXCollections.observableArrayList(RVs));

        if (RVs.isEmpty()) {
            System.out.println("RVs list is empty");
            System.out.println("Current Patient: " + HelloApplication.currentPatientName);
        } else {
            System.out.println("Current Patient: " + HelloApplication.currentPatientName);
            System.out.println("Rendez-vous list size: " + RVs.size());

            for (RendezVousSchema element : RVs){
                System.out.println("RENDEZ-VOUS : " + element.getDate() + " | " + element.getHeure());
            }
        }
    }

    @FXML
    private void handleBackToPatientDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "dossier-patient-view.fxml", 800, 600);
    }
}
