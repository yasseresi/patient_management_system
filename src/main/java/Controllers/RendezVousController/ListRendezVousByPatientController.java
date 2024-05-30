package Controllers.RendezVousController;

import Models.Bilan.BilonSchema;
import Models.DossierPatient.DossierPatientSchema;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.RendezVousSchema;
import Models.RendezVous.SuiviSchema;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ListRendezVousByPatientController {

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

    private String selectedPatient;


    public void setSelectedPatient(String patientName) {
        this.selectedPatient = patientName;
        patientNameLabel.setText("Rendez-vous pour " + selectedPatient);

        System.out.println("rendezvous pour : " + HelloApplication.currentPatientName);

            // Initialize the columns
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            heureColumn.setCellValueFactory(new PropertyValueFactory<>("heure"));
            dureeColumn.setCellValueFactory(new PropertyValueFactory<>("duree"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            observationColumn.setCellValueFactory(new PropertyValueFactory<>("observation"));

            // Placeholder data
        ArrayList<RendezVousSchema> rendezVousList = new ArrayList<>();

        ArrayList<DossierPatientSchema> dossiers ;
        dossiers = HelloApplication.dossierPatientModel.getDossierPatients();

        for (DossierPatientSchema dossier : dossiers) {
            if (dossier.getId().equals(HelloApplication.currentPatientName)) {
                rendezVousList = dossier.getRendezVous().findAll(LocalDate.now());
            }
        }

        if (rendezVousList.isEmpty()) {
            System.out.println("Rendez-vous list is empty");
            rendezVousList = new ArrayList<>(); // Initialize an empty list if null
        }

        // Sample RendezVousSchema objects for testing (replace with actual data retrieval logic)
//        ArrayList<RendezVousSchema> rendezVousListExemple = new ArrayList<>();
//        // Example 1: Today's date
//        rendezVousListExemple.add(new ConsultationSchema(LocalDate.now(), LocalTime.of(12, 30), "1h"));
//
//        // Example 2: Different date
//        rendezVousListExemple.add(new ConsultationSchema(LocalDate.of(2024, 6, 10), LocalTime.of(11, 00), "30min"));

        //rendezVousList.addAll(rendezVousListExemple);

        rendezVousTableView.setItems(FXCollections.observableArrayList(rendezVousList));

        // Add double-click event listener to the TableView
        rendezVousTableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                RendezVousSchema selectedRendezVous = rendezVousTableView.getSelectionModel().getSelectedItem();
                if (selectedRendezVous != null) {
                    try {
                        openRendezVousDetailWindow(selectedRendezVous,selectedPatient);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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

    private void openRendezVousDetailWindow(RendezVousSchema rendezVous , String PatientNameSelected) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("passer-anamnese.fxml"));
        Parent parent = fxmlLoader.load();

//        if ( rendezVous instanceof ConsultationSchema ){
//            System.out.println("Le rendezvous est une consultation");
//            PasserAnamnesController controller = fxmlLoader.getController();
//            controller.setRendezVous(rendezVous);
//
//        } else if ( rendezVous instanceof SuiviSchema) {
//            System.out.println("Le rendezvous est un suivi");
//        }


        PasserAnamnesController controller = fxmlLoader.getController();
        controller.setRendezVous(rendezVous);

        Stage stage = new Stage();
        stage.setTitle("Selected Rendezvous");
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.show();
    }
}
