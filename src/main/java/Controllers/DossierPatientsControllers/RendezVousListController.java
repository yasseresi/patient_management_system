package Controllers.DossierPatientsControllers;

import DataBases.RendezVousFileDB;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.RendezVousModel;
import Models.RendezVous.RendezVousSchema;
import Models.Patient.PatientSchema;
import com.example.patient_management_system.HelloApplication;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

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

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Initialize the table columns
//        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Datee"));
//        heureColumn.setCellValueFactory(new PropertyValueFactory<>("Heuree"));
//        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("Duréee"));
//        observationColumn.setCellValueFactory(new PropertyValueFactory<>("Observationn"));
//        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Typee"));
//    }

    public void setPatientDetails(PatientSchema patient) {

        patientNameLabel.setText(patient.getNom() + " " + patient.getPrenom());
        // EXEMPLE : Create a new RendezVousSchema object
        RendezVousSchema RendezVous1 = new ConsultationSchema(LocalDate.now(), LocalTime.now(),"blidi","aissa",20);

//        patientNameLabel.setText(patient.getNom().toUpperCase() + " " + patient.getPrenom().toUpperCase());
//        // Set other patient details if needed
//
//        // Populate the TableView with rendez-vous information
//        ObservableList<RendezVousSchema> rendezVousList = FXCollections.observableArrayList(patient.getRendezVousList());
//        rendezVousTableView.setItems(rendezVousList);

        System.out.println("Current Patient: " + HelloApplication.currentPatientName);

        if (new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName , HelloApplication.categoryDbFileName).exists()) {
            System.out.println("Rendez-vous file exists");
            HelloApplication.rendezvousModel.load();
            System.out.println("Rendez-vous model has been loaded");
        }

        ArrayList<RendezVousSchema> RVs = HelloApplication.rendezvousModel.findAll(null);
        if (RVs == null) {
            System.out.println("Rendez-vous list is null");
            RVs = new ArrayList<>(); // Initialize an empty list if null
        }

        RVs.add(RendezVous1);
        rendezVousTableView.setItems(FXCollections.observableArrayList(RVs));


        if (RVs.isEmpty()) {
            System.out.println("RVs list is empty");
            System.out.println("Current Patient: " + HelloApplication.currentPatientName);
        } else {
            System.out.println("Current Patient: " + HelloApplication.currentPatientName);

            System.out.println("Rendz-vous list size: " + RVs.size());

            for (RendezVousSchema element : RVs){
                System.out.println(" RENDEZ-VOUS : " +element.getDate() + " | " + element.getHeure());
            }

        }
    }




    @FXML
    private void handleBackToPatientDetails(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dossier-patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
