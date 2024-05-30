package Controllers.RendezVousController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DiagnosticController {

    @FXML
    private ComboBox<String> troubleTypeComboBox;

    @FXML
    private TextField troubleNameField;

    @FXML
    private void initialize() {
        // Initialize ComboBox with trouble types
        troubleTypeComboBox.setItems(FXCollections.observableArrayList("Type 1", "Type 2", "Type 3"));
    }

    @FXML
    private void handleSubmit(javafx.event.ActionEvent actionEvent) {
        // Save the diagnostic information if needed
        String selectedTroubleType = troubleTypeComboBox.getValue();
        String troubleName = troubleNameField.getText();
        System.out.println("Selected Trouble Type: " + selectedTroubleType);
        System.out.println("Trouble Name: " + troubleName);

        // Handle the submission logic
    }
}
