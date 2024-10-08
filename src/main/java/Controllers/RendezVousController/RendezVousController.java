package Controllers.RendezVousController;

import Models.RendezVous.RendezVousSchema;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

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
    private TableColumn<RendezVousSchema, LocalDate> Date;

    @FXML
    private TableColumn<RendezVousSchema, LocalTime> heure;

    @FXML
    private TableColumn<RendezVousSchema, String> dureeColumn;

    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CurrentDate.setText(LocalDate.now().getDayOfMonth() + " " + LocalDate.now().getMonth().toString() + " " + LocalDate.now().getYear());
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        dureeColumn.setCellValueFactory(new PropertyValueFactory<>("duree"));
        heure.setCellValueFactory(new PropertyValueFactory<>("heure"));

        showRendezVousForDate(LocalDate.now());
    }

    @FXML
    public void showRendezVousForDate(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate != null) {
            showRendezVousForDate(selectedDate);
        }
    }

    private void showRendezVousForDate(LocalDate date) {
        Set<RendezVousSchema> rendezVousSet = new HashSet<>(HelloApplication.dossierPatientModel.getRendezVousOfDay(date));
        ObservableList<RendezVousSchema> rendezVousList = FXCollections.observableArrayList(rendezVousSet);

        rendezVousTable.setItems(rendezVousList);
        CurrentDate.setText(date.getDayOfMonth() + " " + date.getMonth().toString() + " " + date.getYear());
    }

    @FXML
    void showTodayRendezVous(ActionEvent event) {
        LocalDate today = LocalDate.now();
        datePicker.setValue(today);
        showRendezVousForDate(today);
    }

    @FXML
    public void AjouterConsultation(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("consultation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1025,800);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ADD Consultation");
        stage.setScene(scene);

        CurrentDate.setText(LocalDate.now().getDayOfMonth() + " " + LocalDate.now().getMonth().toString() + " " + LocalDate.now().getYear());
    }

    @FXML
    public void AjouterSuivi(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("suivi-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1025,800);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ADD Suivi");
        stage.setScene(scene);

        CurrentDate.setText(LocalDate.now().getDayOfMonth() + " " + LocalDate.now().getMonth().toString() + " " + LocalDate.now().getYear());
    }

    @FXML
    public void toAtelier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("atelier-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1025,800);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ADD Suivi");
        stage.setScene(scene);

    }

    @FXML
    public void toPasserRendezVous(ActionEvent event) throws IOException {
        // Load the FXML file for passer-rendezvous
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("passer-rendevouz.fxml" ));
        Scene scene = new Scene(fxmlLoader.load(),600,400);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("PasserRendzVous");
        stage.setScene(scene);
    }

    @FXML
    public void toAccueilPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) accueilButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 835, 549);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void toDossiersPatients(ActionEvent event) throws IOException {
        Stage stage = (Stage) statistiqueButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dossier-patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 835, 549);
        stage.setScene(scene);
        stage.show();
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
    public void toProfilPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) profileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 835, 549);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void toStatistiquePage(ActionEvent event) throws IOException {
        Stage stage = (Stage) statistiqueButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("statistique-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 835, 549);
        stage.setScene(scene);
        stage.show();
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
}





