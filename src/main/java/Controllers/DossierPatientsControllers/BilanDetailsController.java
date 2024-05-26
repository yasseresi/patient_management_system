package Controllers.DossierPatientsControllers;

import Utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import Models.Bilan.BilonSchema;
import javafx.stage.Stage;

import java.io.IOException;

public class BilanDetailsController {

    @FXML
    private TreeTableView<String> anamnesisTreeTableView;

    @FXML
    private TreeTableView<String> testTreeTableView;

    @FXML
    private TreeTableView<String> observationTreeTableView;

    @FXML
    private TreeTableView<String> diagnosticTreeTableView;

    public void initData(BilonSchema bilan) {
        populateTreeTableView(anamnesisTreeTableView, "Anamnesis", bilan.getAnamneseModel().getQuestionsAdulte().toString());
        populateTreeTableView(testTreeTableView, "Test", bilan.getTests().getAllTests().toString());
        populateTreeTableView(observationTreeTableView, "Observation", bilan.getObservations().toString());
        populateTreeTableView(diagnosticTreeTableView, "Diagnostic", bilan.getDiagnostic().getTroubles().toString());
    }

    private void populateTreeTableView(TreeTableView<String> treeTableView, String title, String data) {
        // Create root item
        TreeItem<String> rootItem = new TreeItem<>(title);

        // Create child item with the data
        TreeItem<String> dataItem = new TreeItem<>(data);

        // Add child item to root
        rootItem.getChildren().add(dataItem);

        // Set root item to tree table view
        treeTableView.setRoot(rootItem);
    }

    @FXML
    private void handleBackToPatientDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "dossier-patient-view.fxml", 800, 600);
    }
}
