package Controllers.RendezVousController;

import Exceptions.ConsultationAlreadyCreatedExecption;
import Exceptions.ConsultationAlreadyPassedExecption;
import Exceptions.PatientAlreadyExistException;
import Models.Patient.AdultSchema;
import Models.Patient.EnfantSchema;
import Models.Patient.PatientSchema;
import Models.RendezVous.ConsultationSchema;
import Utils.Popups;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class CreateConsultationController implements Initializable {

    @FXML
    private TextField age;

    @FXML
    private DatePicker dateNaissance;

    @FXML
    private TextField lieuNaissance;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private DatePicker rdvDate;

    @FXML
    private Text rdvForm1;

    @FXML
    private ComboBox<Integer> rdvHeur;

    @FXML
    private ComboBox<Integer> rdvMin;

    @FXML
    private TextField tf_adresse;

    @FXML
    private TextField tf_tele_one;


    @FXML
    void CreerConsultation(ActionEvent event) throws IOException {

        String nom = this.nom.getText();
        System.out.println("nom is :" + nom);
        String prenom = this.prenom.getText();
        String lieu = this.lieuNaissance.getText();
        LocalDate birthDday = this.dateNaissance.getValue();
        String adresse = this.tf_adresse.getText();
        // Validate that the telephone input is a valid integer
        int nbTelephone;
        try {
            nbTelephone = Integer.parseInt(this.tf_tele_one.getText());


            // Validate that the age input is a valid integer
            int age = 0;

            age = Integer.parseInt(this.age.getText());

            LocalDate rdvDay = this.rdvDate.getValue();
            LocalTime rdvTime = LocalTime.of(this.rdvHeur.getValue(), this.rdvMin.getValue());


            PatientSchema patient;
            patient = (age <= 13) ? new EnfantSchema(nom, prenom, age, birthDday, lieu, adresse, nbTelephone, true) : new AdultSchema(nom, prenom, age, birthDday, lieu, adresse, nbTelephone, true);

            String duration;
            duration = (patient instanceof EnfantSchema) ? "1:30 min" : "2:30 min";

            ConsultationSchema rendezVousSchema = new ConsultationSchema(rdvDay, rdvTime, duration);


            HelloApplication.patientModel.create(patient);
            System.out.println("patient " + patient.getPrenom() + " " + patient.getNom() + " is created");

            HelloApplication.dossierPatientModel.creerDossierPatient(patient);


            HelloApplication.dossierPatientModel.CreerConsultation(rendezVousSchema, patient);
        } catch (NumberFormatException e) {
            Popups.showErrorMessage("please enter a correct phone number or age format");
        } catch (PatientAlreadyExistException e) {
            Popups.showErrorMessage(e.getMessage());
        } catch (ConsultationAlreadyCreatedExecption e) {
            throw new RuntimeException(e);
        }


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rendez-vous-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rendez vous");
        stage.setScene(scene);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate the hours ComboBox
        IntStream.range(0, 24).forEach(i -> rdvHeur.getItems().add(i));
        rdvHeur.getSelectionModel().selectFirst();

        // Populate the minutes ComboBox with intervals of 5 minutes
        IntStream.iterate(0, i -> i + 5).limit(12).forEach(i -> rdvMin.getItems().add(i));
        rdvMin.getSelectionModel().selectFirst();

    }


}
