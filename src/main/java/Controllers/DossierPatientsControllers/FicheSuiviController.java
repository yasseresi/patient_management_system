package Controllers.DossierPatientsControllers;

import Models.DossierPatient.DossierPatientSchema;
import Models.Objectif.FichSuiviSchema;
import Models.Objectif.ObjectifShema;
import Models.Patient.PatientSchema;
import Utils.SceneSwitcher;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class FicheSuiviController {

    @FXML
    private Label patientNameLabel;

    @FXML
    private VBox chartContainer;

    @FXML
    private ScrollPane scrollPane; // Add this line to reference the ScrollPane

    @FXML
    private ListView<FichSuiviSchema> fichSuiviListView;

    private ObservableList<FichSuiviSchema> fichSuiviObservableList = FXCollections.observableArrayList();

    public void setPatientDetails(PatientSchema patient) {

        // Set patient name label
        patientNameLabel.setText(patient.getNom().toUpperCase() + " " + patient.getPrenom().toUpperCase() + "   |   " + LocalDate.now());

        ArrayList<DossierPatientSchema> dossiers = HelloApplication.dossierPatientModel.getDossierPatients();

        for (DossierPatientSchema dossier : dossiers) {
            if (dossier.getId().equals(HelloApplication.currentPatientName)) {
                fichSuiviObservableList.addAll(dossier.getFichSuivi().getFichsuivis());
                System.out.println("id : " + dossier.getId().toUpperCase());
            }
        }

        if (fichSuiviObservableList.isEmpty()) {
            System.out.println("FichSuivis list is empty");
        } else {
            System.out.println("FichSuivis list size: " + fichSuiviObservableList.size());

            for (FichSuiviSchema element : fichSuiviObservableList){
                System.out.println("FichSuivi : " + element.toString());
            }
        }

        // Populate ListView with FichSuivis
        fichSuiviListView.setItems(fichSuiviObservableList);

        // Add click event listener for ListView items
        fichSuiviListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleFichSuiviClick(event);
            }
        });    }

    private void handleFichSuiviClick(MouseEvent event) {
        FichSuiviSchema selectedFichSuivi = fichSuiviListView.getSelectionModel().getSelectedItem();
        if (selectedFichSuivi != null) {
            openFichSuiviWindow(selectedFichSuivi);
        }
    }

    private void openFichSuiviWindow(FichSuiviSchema fichSuivi) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("objectifs-view.fxml"));
            Parent root = loader.load();

            FichSuiviObjectifsController controller = loader.getController();
            controller.setFichSuivi(fichSuivi);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Objectifs");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToPatientDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "dossier-patient-view.fxml", 800, 600);
    }
}
