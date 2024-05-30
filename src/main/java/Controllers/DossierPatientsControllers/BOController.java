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
import Models.DossierPatient.DossierPatientSchema;
import Models.Objectif.FichSuiviSchema;
import Models.ObservationsCliniques.ObservationModel;
import Models.Question.*;
import Models.RendezVous.RendezVousSchema;
import Models.Test.TestModel;
import Models.Test.TestSchema;
import Models.Patient.PatientSchema;
import Models.Test.TestSchemaQuestionaire;
import Utils.SceneSwitcher;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.FXCollections;
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


    public void setPatientDetails(PatientSchema patient) {

        patientNameLabel.setText(HelloApplication.currentPatientName.toUpperCase());

        ArrayList<BilonSchema> bilans = new ArrayList<>();

        ArrayList<DossierPatientSchema> dossiers = HelloApplication.dossierPatientModel.getDossierPatients();

        for (DossierPatientSchema dossier : dossiers) {
            if (dossier.getId().equals(HelloApplication.currentPatientName)) {
                bilans = dossier.getBilan().getBilans();
                System.out.println("id : " + dossier.getId().toUpperCase());
            }
        }

        if (bilans.isEmpty()) {
            System.out.println("Bilans list is empty");
        } else {
            System.out.println("Bilans list size: " + bilans.size());

            for (BilonSchema element : bilans){
                System.out.println("Bilan.Tests.Test1.Nom : " + element.getTests().getAllTests().getFirst().getNom());
            }
        }

        // Populate ListView with Bilans
        bilanListView.setItems(FXCollections.observableArrayList(bilans));



        // ------------------ Example data for testing ------------------------------
//        AnamneseModel anamneseModel = new AnamneseModel(new AnamneseDBFile());
//        try {
//            anamneseModel.createQuestion(new QuestionEnfant("Question 1", TypeEnfant.ANTECEDENTS_FAMILIAUX));
//            anamneseModel.createQuestion(new QuestionEnfant("Question 2", TypeEnfant.CARACTERE_ET_COMPORTEMENT));
//            anamneseModel.createQuestion(new QuestionEnfant("Question 3", TypeEnfant.DEVELOPPEMENT_PSYCHOMOTEUR));
//        } catch (QuestionAlreadyExistException e) {
//            e.printStackTrace();
//        }
//
//        TestModel testModel = new TestModel(new TestFileDB());
//        try {
//            testModel.createTest( new TestSchemaQuestionaire("Test Questionaire"));
//        } catch (TestNomUniqueException e) {
//            e.printStackTrace();
//        }
//
//        ObservationModel observationModel = new ObservationModel(new ArrayList<>());
//        observationModel.addObservation("Observation 1");
//
//        TroubleModel troubleModel = new TroubleModel(new TreeMap<>());
//        troubleModel.addTroubles("Trouble 1", TypeTrouble.CONGNITIFS);
//
//        BilonSchema bilan = new BilonSchema(anamneseModel, testModel, observationModel, troubleModel);
//        ArrayList<BilonSchema> bilans_exemple = new ArrayList<>();
//        bilans_exemple.add(bilan);

        // Combine example data with loaded data
        //bilans.addAll(bilans_exemple);
        //System.out.println("EX : Bilan.Anamnese.QuestionFirst : " + bilan.getAnamneseModel().getQuestionsEnfant().getFirst().getQuestion().toUpperCase());

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
            Label anamneseLabel = new Label("Anamnese (Adulte) : " + bilan.getAnamneseModel().getQuestionsEnfant().getFirst().getQuestion().toUpperCase());
            Label testLabel = new Label("Tests: " + bilan.getTests().getAllTests().toString());
            Label observationLabel = new Label("Observations: " + bilan.getObservations().toString());
            Label diagnosticLabel = new Label("Diagnostic.Trouble.First.Type: " + bilan.getDiagnostic().getTroubles().firstEntry().getValue().getCategorie().toString());

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
