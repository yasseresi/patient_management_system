package Controllers.DossierPatientsControllers;

import Models.Diagnostique.TroubleSchema;
import Models.Question.QuestionAdult;
import Models.Question.QuestionEnfant;
import Models.Test.TestSchema;
import Utils.SceneSwitcher;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Models.Bilan.BilonSchema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BilanDetailsController {

    @FXML
    private TableView<AnamnesisItem> anamnesisTableView ;

    @FXML
    private TableColumn<AnamnesisItem, String> typeColumn;

    @FXML
    private TableColumn<AnamnesisItem, String> questionColumn;

    @FXML
    private TableColumn<AnamnesisItem, String> responseColumn;

    @FXML
    private ListView<AnamnesisItem> anamnesisListView;

    @FXML
    private ListView<String> testListView;

    @FXML
    private ListView<String> observationListView;

    @FXML
    private ListView<String> diagnosticListView;

    public void initData(BilonSchema bilan) {

        if (HelloApplication.patientModel.getPatientById(HelloApplication.currentPatientName).getAge() < 13) {
            populateAnamnesisEnfantTableView(bilan.getAnamneseModel().getQuestionsEnfant());
            System.out.println(" Type est Enfant ");
        } else {
            populateAnamnesisAdulteTableView(bilan.getAnamneseModel().getQuestionsAdulte());
            System.out.println(" Type est Adult ");
        }

        populateAnamnesisEnfantTableView(bilan.getAnamneseModel().getQuestionsEnfant());

        populateTestListView(bilan.getTests().getAllTests());
        populateObservationListView(bilan.getObservations().getObservations());
        populateDiagnosticListView(bilan.getDiagnostic().getTroubles());
    }

    private void populateAnamnesisAdulteTableView(ArrayList<QuestionAdult> data) {
        List<AnamnesisItem> items = new ArrayList<>();
        for (QuestionAdult item : data) {
            items.add( new AnamnesisItem(item.getTypeQuestion().toString() , item.getQuestion() , item.getResponse()) );
        }
        anamnesisListView.setItems(FXCollections.observableArrayList(items));
    }

    private void populateAnamnesisEnfantTableView(ArrayList<QuestionEnfant> data) {
        List<AnamnesisItem> items = new ArrayList<>();
        for (QuestionEnfant item : data) {
            items.add( new AnamnesisItem(item.getTypeQuestion().toString() , item.getQuestion() , item.getReponse()) );
        }
        anamnesisListView.setItems(FXCollections.observableArrayList(items));
    }

    private void populateTestListView(List<TestSchema> data) {
        List<String> items = new ArrayList<>();
        for (TestSchema item : data) {
            items.add(item.getNom());
        }
        testListView.setItems(FXCollections.observableArrayList(items));
    }

    private void populateObservationListView(List<String> data) {
        observationListView.setItems(FXCollections.observableArrayList(data));
    }

    private void populateDiagnosticListView(Map<String, TroubleSchema> data) {
        List<String> items = new ArrayList<>();
        for (Map.Entry<String, TroubleSchema> entry : data.entrySet()) {
            String key = entry.getKey();
            TroubleSchema value = entry.getValue();
            items.add(value.getNom() + " | Type : " + value.getCategorie().toString().toUpperCase());
        }
        diagnosticListView.setItems(FXCollections.observableArrayList(items));
    }

    @FXML
    private void handleBackToPatientDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "dossier-patient-view.fxml", 800, 600);
    }

    public static class AnamnesisItem {
        private String type;
        private String question;
        private String response;

        public AnamnesisItem(String type, String question, String response) {
            this.type = type;
            this.question = question;
            this.response = response;
        }

    }
}
