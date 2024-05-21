package Controllers.RendezVousController;

import Models.RendezVous.RendezVousSchema;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RendezVousController implements Initializable {


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

    @FXML
    private Label CurrentDate;
    @FXML
    private TableView<RendezVousSchema> rendezVousTable;

    @FXML
    private TableColumn<?, ?> FullName;

    @FXML
    private TableColumn<?, ?> TypeConlumn;

    @FXML
    private TableColumn<?, ?> dureeColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CurrentDate.setText(LocalDate.now().getDayOfMonth() + " " + LocalDate.now().getMonth().toString() + " " + LocalDate.now().getYear());
    }






    @FXML
    public void toTests(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tests-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);
    }


    @FXML
    public void toAnamnese(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("anamnese-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);
    }
    @FXML
    void toAccueilPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("HomePage");
        stage.setScene(scene);


    }

    @FXML
    void toLogOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("login");
        stage.setScene(scene);
        HelloApplication.currentUserName = null;
        System.out.println("Logged out successfully");

    }

    @FXML
    void toProfilPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profil-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Profil");
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
    void toStatistiquePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("statistique-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);


    }

}
