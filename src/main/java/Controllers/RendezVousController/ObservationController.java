package Controllers.RendezVousController;

import com.example.patient_management_system.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class ObservationController {

    @FXML
    private TextField observationField;

    @FXML
    private void handleNext(javafx.event.ActionEvent event) {
        // Save the observation if needed
        String observation = observationField.getText();
        System.out.println("Observation: " + observation);

        HelloApplication.observationModel.addObservation( observation );

        HelloApplication.bilonSchema.setObservations( HelloApplication.observationModel );
        //HelloApplication.bilonModel.addBilan( HelloApplication.bilonSchema );

        System.out.println(" Hello.ObservationModel = " + HelloApplication.observationModel.getObservations().getFirst().toUpperCase());

        // Load the diagnostic view
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("diagnostic-view.fxml" ));
            Scene scene = new Scene(fxmlLoader.load(),600,400);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("PasserRendzVous");
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
