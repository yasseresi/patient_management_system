package Controllers.DossierPatientsControllers;

import Models.Patient.PatientSchema;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class BOController {

    @FXML
    private Label patientNameLabel;

    public void setPatientDetails(PatientSchema patient) {
        patientNameLabel.setText(patient.getNom().toUpperCase() + " " + patient.getPrenom().toUpperCase());
        // Set other patient details if needed
    }

    @FXML
    private void handleBackToPatientDetails(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dossier-patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}