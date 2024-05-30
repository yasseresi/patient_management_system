package Controllers.RendezVousController;

import Models.Diagnostique.TypeTrouble;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DiagnosticController {

    @FXML
    private ComboBox<String> troubleTypeComboBox;

    @FXML
    private TextField troubleNameField;

    @FXML
    private void initialize() {
        // Populate the ComboBox with TypeTrouble enum values
        ObservableList<String> troubleTypes = FXCollections.observableArrayList();
        for (TypeTrouble type : TypeTrouble.values()) {
            troubleTypes.add(type.toString());
        }
        troubleTypeComboBox.setItems(troubleTypes);
    }

    @FXML
    private void handleSubmit(javafx.event.ActionEvent actionEvent) throws IOException {
        // Save the diagnostic information
        String selectedTroubleType = troubleTypeComboBox.getValue();
        String troubleName = troubleNameField.getText();
        System.out.println("Selected Trouble Type: " + selectedTroubleType);
        System.out.println("Trouble Name: " + troubleName);

        HelloApplication.troubleSchema.setNom(troubleName);
        HelloApplication.troubleSchema.setCategorie(selectedTroubleType);
        HelloApplication.troubleModel.addTrouble(HelloApplication.troubleSchema);

        HelloApplication.bilonSchema.setDiagnostic(HelloApplication.troubleModel);

        HelloApplication.bilonModel.addBilan(HelloApplication.bilonSchema);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rendez-vous-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Rendez vous");
        stage.setScene(scene);
    }
}