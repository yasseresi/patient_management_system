package Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(Stage stage, String fxmlFile, double width, double height) throws IOException {
        // Ensure the resource path is correct
        FXMLLoader fxmlLoader = new FXMLLoader(SceneSwitcher.class.getResource("/com/example/patient_management_system/" + fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setScene(scene);
        stage.centerOnScreen(); // Ensure the stage remains centered
    }
}
