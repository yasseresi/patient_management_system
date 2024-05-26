package Controllers.DossierPatientsControllers;

import DataBases.AnamneseDBFile;
import DataBases.TestFileDB;
import Exceptions.QuestionAlreadyExistException;
import Exceptions.TestNomUniqueException;
import Models.Anamnese.AnamneseModel;
import Models.Bilan.BilonModel;
import Models.Bilan.BilonSchema;
import Models.Diagnostique.TroubleModel;
import Models.Diagnostique.TypeTrouble;
import Models.ObservationsCliniques.ObservationModel;
import Models.Question.QuestionAdult;
import Models.Question.QuestionAnamnese;
import Models.Question.TypeAdult;
import Models.RendezVous.RendezVousSchema;
import Models.Test.TestModel;
import Models.Test.TestSchema;
import Models.Patient.PatientSchema;
import Models.Test.TestSchemaQuestionaire;
import Utils.SceneSwitcher;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.TreeMap;

public class BOController {

    @FXML
    private Label patientNameLabel;

    @FXML
    private ListView<BilonSchema> bilanListView; // List view for displaying bilans

    @FXML
    private VBox bilanDetailContainer; // Container for displaying bilan details

    private BilonModel bilanModel;

    public void setPatientDetails(PatientSchema patient) {
        patientNameLabel.setText(patient.getNom().toUpperCase() + " " + patient.getPrenom().toUpperCase());

        bilanModel = new BilonModel();
        try {
            if (new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName, HelloApplication.boDBFileName).exists()) {
                System.out.println("Bilan file exists");
                bilanModel.load();
                System.out.println("Bilan model has been loaded");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<BilonSchema> bilansss = HelloApplication.bilonModel.getBilans();

        // ------------------ Example data for testing ------------------------------
        AnamneseModel anamneseModel = new AnamneseModel(new AnamneseDBFile());
        try {
            anamneseModel.createQuestion(new QuestionAdult("Question 1", TypeAdult.HISTOIRE_DE_MALADIE));
        } catch (QuestionAlreadyExistException e) {
            e.printStackTrace();
        }

        TestModel testModel = new TestModel(new TestFileDB());
        try {
            testModel.createTest( new TestSchemaQuestionaire("Test Questionaire"));
        } catch (TestNomUniqueException e) {
            e.printStackTrace();
        }

        ObservationModel observationModel = new ObservationModel(new ArrayList<>());
        observationModel.addObservation("Observation 1");

        TroubleModel troubleModel = new TroubleModel(new TreeMap<>());
        troubleModel.addTroubles("Trouble 1", TypeTrouble.CONGNITIFS);

        BilonSchema bilan = new BilonSchema(anamneseModel, testModel, observationModel, troubleModel);
        ArrayList<BilonSchema> bilans = new ArrayList<>();
        bilans.add(bilan);

        // Combine example data with loaded data
        bilans.addAll(bilanModel.getBilans());

        displayBilans(bilans);

        bilanListView.setOnMouseClicked(event -> {
            if (event != null && event.getClickCount() == 2 && bilanListView.getSelectionModel().getSelectedItem() != null) {
                BilonSchema selectedBilan = bilanListView.getSelectionModel().getSelectedItem();
                handleViewBilanDetails(selectedBilan , event);

//                HelloApplication.currentPatientName = selectedPatient.getNom() + " " + selectedPatient.getPrenom() ;
            }
        });
    }

    private void displayBilans(ArrayList<BilonSchema> bilans) {
        bilanListView.getItems().setAll(bilans);
        bilanListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> displayBilanDetails(newValue));
    }

    private void displayBilanDetails(BilonSchema bilan) {
        bilanDetailContainer.getChildren().clear();

        if (bilan != null) {
            // Display bilan details in the container
            Label anamneseLabel = new Label("Anamnese (Adulte): " + bilan.getAnamneseModel().getQuestionsAdulte().toString());
            Label testLabel = new Label("Tests: " + bilan.getTests().getAllTests().toString());
            Label observationLabel = new Label("Observations: " + bilan.getObservations().toString());
            Label diagnosticLabel = new Label("Diagnostic: " + bilan.getDiagnostic().getTroubles().toString());

            bilanDetailContainer.getChildren().addAll(anamneseLabel, testLabel, observationLabel, diagnosticLabel);
        }
    }


    @FXML
    private void handleViewBilanDetails(BilonSchema selectedBilan , EventObject event) {

        if (selectedBilan != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("bilan-details.fxml" ));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Bilan Details");
                stage.setScene(scene);

                BilanDetailsController controller = fxmlLoader.getController();
                controller.initData(selectedBilan);

                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleBackToPatientDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "dossier-patient-view.fxml", 800, 600);
    }
}
