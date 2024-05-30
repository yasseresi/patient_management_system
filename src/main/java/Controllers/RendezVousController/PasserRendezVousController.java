package Controllers.RendezVousController;

import Models.Bilan.BilonSchema;
import Models.Patient.PatientSchema;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PasserRendezVousController {

    @FXML
    private ComboBox<String> patientComboBox;

    @FXML
    private Button backButton;

    @FXML
    private Button validateButton;


    public void initialize() {
        ObservableList<PatientSchema> ListPatients = HelloApplication.patientModel.getPatients();

        // Create a new ObservableList to store formatted patient names
        ObservableList<String> formattedPatientNames = FXCollections.observableArrayList();

        // Loop through patients and format their names
        for (PatientSchema patient : ListPatients) {
            String formattedName = patient.getNom() + " " + patient.getPrenom();
            formattedPatientNames.add(formattedName);
        }

        // Set the formatted names as items in the ComboBox
        patientComboBox.setItems(formattedPatientNames);
    }

    @FXML
    public void onBackButtonClicked(ActionEvent event) throws IOException {
        // Load the FXML file for rendez-vous-view (replace with your actual logic)
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rendez-vous-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void onValidateButtonClicked(ActionEvent event) throws IOException {
        String selectedPatientName = patientComboBox.getSelectionModel().getSelectedItem();
        System.out.println("patient selected : " + selectedPatientName);

        HelloApplication.currentPatientName = selectedPatientName ;

        // Implement logic to change window content based on selected patient
        if (selectedPatientName != null) {
            // Load FXML for the new window (list-rendezvous-by-patient.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("list-rendezvous-byPatient.fxml"));
            Parent root = fxmlLoader.load();

            // Pass the selected patient name to the new controller (replace with your actual logic)
            ListRendezVousByPatientController newWindowController = fxmlLoader.getController();  // Assuming you have a new window controller
            newWindowController.setSelectedPatient(selectedPatientName);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
