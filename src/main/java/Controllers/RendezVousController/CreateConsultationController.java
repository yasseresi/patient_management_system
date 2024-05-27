package Controllers.RendezVousController;

import Exceptions.ConsultationAlreadyPassedExecption;
import Exceptions.ConsultationFirstException;
import Models.Patient.AdultSchema;
import Models.Patient.EnfantSchema;
import Models.Patient.PatientSchema;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.RendezVousSchema;
import Utils.Popups;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
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
        String prenom = this.prenom.getText();
        String lieu = this.lieuNaissance.getText();
        LocalDate birthDday = this.dateNaissance.getValue();
        String adresse = this.tf_adresse.getText();
        // Validate that the telephone input is a valid integer
        int nbTelephone;
        try {
            nbTelephone = Integer.parseInt(this.tf_tele_one.getText());
        } catch (NumberFormatException e) {
            Popups.showErrorMessage("Please enter a valid telephone number.");
            return; // Exit the method if the input is not a valid integer
        }

        // Validate that the age input is a valid integer
        int age;
        try {
            age = Integer.parseInt(this.age.getText());
        } catch (NumberFormatException e) {
            Popups.showErrorMessage("Please enter a valid age.");
            return; // Exit the method if the input is not a valid integer
        }
        LocalDate rdvDay = this.rdvDate.getValue();
        LocalTime rdvTime = LocalTime.of(this.rdvHeur.getValue(),this.rdvMin.getValue());


        PatientSchema patient ;
        patient = (age <= 13) ? new EnfantSchema(nom, prenom, age, birthDday, lieu, adresse, nbTelephone, true) : new AdultSchema(nom, prenom, age, birthDday, lieu, adresse, nbTelephone, true);

        Duration duration ;
        duration = (patient instanceof EnfantSchema) ? Duration.ofMinutes(90) : Duration.ofMinutes(150);

        RendezVousSchema rendezVousSchema = new ConsultationSchema(rdvDay,rdvTime,duration);

        try {
            HelloApplication.dossierPatientModel.CreerRendezVous(rendezVousSchema,patient);
            HelloApplication.patientModel.create(patient);
            System.out.println("patient is :" +patient.toString());
            System.out.println(" rendez Vous details:"+ rendezVousSchema.toString());
        } catch (ConsultationFirstException e) {
            Popups.showErrorMessage(e.getMessage());
        } catch (ConsultationAlreadyPassedExecption e) {
            Popups.showErrorMessage(e.getMessage());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rendez-vous-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
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
