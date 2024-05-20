package Controllers.DossierPatientsControllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Models.Patient.PatientSchema;
import com.example.patient_management_system.HelloApplication;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DossierPatientController implements Initializable {



    @FXML
    private Button addPatientButton;

        @FXML
        private Button DosssierPatientbutton;

        @FXML
        private Button accueilButton;
        @FXML
        private TableView<PatientSchema> patientTable;

        @FXML
        private TableColumn<PatientSchema, LocalDate> dateNaissance;

        @FXML
        private TableColumn<PatientSchema  , String> lieuNaissance;

        @FXML
        private Button logOutButton;

        @FXML
        private TableColumn<PatientSchema, String> noms;

        @FXML
        private TableColumn<PatientSchema, Boolean> nouveau;

        @FXML
        private TableColumn<PatientSchema, String> prenom;

        @FXML
        private Button profileButton;

        @FXML
        private Button rendezVousButtom;

        @FXML
        private Button statistiqueButton;

        @FXML
        private TableColumn<PatientSchema, String> typepatients;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       if(new File(HelloApplication.usersDirectoryName +"/"+HelloApplication.currentUserName,HelloApplication.patientsDBFileName).exists()) {

           try {
               HelloApplication.patientModel.load();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
           System.out.println(" patient model has been loaded");
       }
        noms.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        lieuNaissance.setCellValueFactory(new PropertyValueFactory<>("lieuNaissance"));
        nouveau.setCellValueFactory(new PropertyValueFactory<>("nouveau"));
        typepatients.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));


        ObservableList<PatientSchema> patients = HelloApplication.patientModel.getPatients();
           patientTable.setItems(patients);

    }

            @FXML
            void addPatient(ActionEvent event) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-patient.fxml" ));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.setTitle("addPatient");
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
        void toLogOut(javafx.event.ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),800,600);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("login");
            stage.setScene(scene);
            //todo:save the files
            HelloApplication.patientModel.save();
            HelloApplication.currentUserName = null;
            System.out.println("Logged out successfully");

        }

        @FXML
        void toProfilPage(javafx.event.ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profil-page-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),800,600);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Profil");
            stage.setScene(scene);


        }

        @FXML
        void toRendezVousPage(javafx.event.ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rendez-vous-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),800,600);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Rendez vous");
            stage.setScene(scene);


        }

        @FXML
        void toStatistiquePage(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("statistique-page-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),800,600);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("statistique");
            stage.setScene(scene);


        }


}
