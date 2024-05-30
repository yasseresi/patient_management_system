package Controllers.HomeController;

import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AcuilePageController  implements Initializable {
    @FXML
    private Label OrthophonisteName;

    @FXML
    private Button accueilButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button rendezVousButtom;

    @FXML
    private Button statistiqueButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OrthophonisteName.setText(HelloApplication.currentUserName);
    }


    @FXML
    public void toTests(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tests-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1025,800);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);
    }


    @FXML
    public void toAnamnese(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("anamnese-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1025,800);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);
    }
    @FXML
    void toDossiersPatients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dossier-patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Patients");
        stage.setScene(scene);
    }

    @FXML
    void toLogOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1025,800);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("login");
        stage.setScene(scene);
        System.out.println("Logged out successfully");
        HelloApplication.patientModel.save();
        HelloApplication.testquestions.save();
        HelloApplication.testexercice.save();
        HelloApplication.anamneseModel.save();
        System.out.println("Files saved succeessfully");
        HelloApplication.currentUserName = null;
        HelloApplication.currentPatientName = null;
        System.out.println("the current user after logout is "+HelloApplication.currentUserName);
        System.out.println("the current patient after logout is "+HelloApplication.currentPatientName);

    }

    @FXML
    void toProfilPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1025,800);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Profil");
        stage.setScene(scene);


    }

    @FXML
    void toRendezVousPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rendez-vous-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1025,800);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rendez vous");
        stage.setScene(scene);


    }

    @FXML
    void toStatistiquePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1025,800);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);


    }



}
