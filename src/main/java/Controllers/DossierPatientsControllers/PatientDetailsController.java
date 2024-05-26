package Controllers.DossierPatientsControllers;

import Models.Patient.PatientSchema;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientDetailsController {

    @FXML
    private Label patientNameLabel;
    @FXML
    private Label patientAgeLabel;
    @FXML
    private Label patientDateNaissanceLabel;
    @FXML
    private Label patientLieuNaissanceLabel;
    @FXML
    private Button rendezVousButton;
    @FXML
    private Button boButton;
    @FXML
    private Button ficheSuiviButton;

    private PatientSchema patient;

    @FXML
    private void initialize() {
        // Initialize if necessary
    }

    public void setPatientDetails(PatientSchema patient) {
        this.patient = patient;  // Store the patient object
        patientNameLabel.setText(patient.getNom().toUpperCase() + " " + patient.getPrenom().toUpperCase());
        patientAgeLabel.setText(String.valueOf(patient.getAge()) + " ans");
        patientDateNaissanceLabel.setText(patient.getDateNaissance().toString());
        patientLieuNaissanceLabel.setText(patient.getLieuNaissance());
    }

    @FXML
    void handleRendezVous(ActionEvent event) {
        loadNewContent("rendez-vous-list.fxml", "Rendez-vous", patient);
    }

    @FXML
    void handleBO(ActionEvent event) {
        loadNewContent("bo.fxml", "BO", patient);
    }

    @FXML
    void handleFicheSuivi(ActionEvent event) {
        loadNewContent("fiche-suivi.fxml", "Fiche de suivi", patient);
    }

    private void loadNewContent(String fxmlFile, String title, PatientSchema patient) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            Scene newScene = new Scene(loader.load());

            // Get the controller of the new scene
            Object controller = loader.getController();
            if (controller instanceof RendezVousListController) {
                ((RendezVousListController) controller).setPatientDetails(patient);
            } else if (controller instanceof BOController) {
                ((BOController) controller).setPatientDetails(patient);
            } else if (controller instanceof FicheSuiviController) {
                ((FicheSuiviController) controller).setPatientDetails(patient);
            }

            // Get the current stage
            Stage currentStage = (Stage) patientNameLabel.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.setTitle(title);
            currentStage.centerOnScreen();
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
