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
        ObservableList<RendezVousSchema> rendezVousList = FXCollections.observableArrayList();
        rendezVousList.addAll(HelloApplication.dossierPatientModel.getRendezVousOfDay(date));


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
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ADD Consultation");
        stage.setScene(scene);

        CurrentDate.setText(LocalDate.now().getDayOfMonth() + " " + LocalDate.now().getMonth().toString() + " " + LocalDate.now().getYear());
    }

    @FXML
    public void AjouterSuivi(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("suivi-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ADD Suivi");
        stage.setScene(scene);

        CurrentDate.setText(LocalDate.now().getDayOfMonth() + " " + LocalDate.now().getMonth().toString() + " " + LocalDate.now().getYear());
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
        Scene scene = new Scene(fxmlLoader.load(),800,600);
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 835, 549);
        stage.setScene(scene);
        stage.show();
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
}








//@Override
//public void initialize(URL url, ResourceBundle resourceBundle) {
//    CurrentDate.setText(LocalDate.now().getDayOfMonth() + " " + LocalDate.now().getMonth().toString() + " " + LocalDate.now().getYear());
//    Date.setCellValueFactory(new PropertyValueFactory<>("date"));
//    dureeColumn.setCellValueFactory(new PropertyValueFactory<>("duree"));
//    heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
//
//    ObservableList<RendezVousSchema> rendezVous = FXCollections.observableArrayList();
//    rendezVous.addAll(HelloApplication.dossierPatientModel.getRendezVousOfToday());
//    System.out.println("liste des id stokee dans le model de dossierPatients");
//    HelloApplication.dossierPatientModel.toString();
//    System.out.println("liste des rendez Vous stocke dans la liste observable");
//    for (int i = 0; i < rendezVous.toArray().length; i++) {
//        System.out.println(rendezVous.get(i).toString());
//    }
//
//    rendezVousTable.setItems(rendezVous);
//
//}