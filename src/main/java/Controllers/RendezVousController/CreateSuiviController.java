package Controllers.RendezVousController;

import Exceptions.ConsultationFirstException;
import Exceptions.PatientAlreadyExistException;
import Exceptions.PatientDoesNotExistException;
import Exceptions.UserDoesNotExistException;
import Models.Patient.PatientSchema;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.DeroulementSuivi;
import Models.RendezVous.RendezVousSchema;
import Models.RendezVous.SuiviSchema;
import Utils.Popups;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class CreateSuiviController  implements Initializable {

    public SplitMenuButton type;
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

    private DeroulementSuivi selectedType;


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate the hours ComboBox
        IntStream.range(0, 24).forEach(i -> rdvHeur.getItems().add(i));
        rdvHeur.getSelectionModel().selectFirst();

        // Populate the minutes ComboBox with intervals of 5 minutes
        IntStream.iterate(0, i -> i + 5).limit(12).forEach(i -> rdvMin.getItems().add(i));
        rdvMin.getSelectionModel().selectFirst();

        // Populate the SplitMenuButton with enum values
        for (DeroulementSuivi typeValue : DeroulementSuivi.values()) {
            MenuItem menuItem = new MenuItem(typeValue.name());
            menuItem.setOnAction(event -> {
                type.setText(typeValue.name());
                selectedType = typeValue;
            });
            type.getItems().add(menuItem);
        }
    }

    @FXML
    void CreerSuivi(ActionEvent event) throws IOException {
        String nom = this.nom.getText();
        String prenom = this.prenom.getText();
        LocalDate date = rdvDate.getValue();
        int heure = rdvHeur.getValue();
        int min = rdvMin.getValue();
        PatientSchema patient;

        if (selectedType == null) {
            Popups.showErrorMessage("Veuillez sélectionner le type de suivi (Suivi).");
            return;
        }

        try {
            patient = HelloApplication.patientModel.find(nom, prenom);
            if(patient == null) throw new PatientDoesNotExistException();
            if (patient.isNouveau()) throw new ConsultationFirstException();

            SuiviSchema rendezVous = new SuiviSchema(date,LocalTime.of(heure,min),selectedType);

            HelloApplication.dossierPatientModel.CreerSuivi(rendezVous,patient);


        } catch (PatientDoesNotExistException e) {
            Popups.showErrorMessage(e.getMessage());
        } catch (ConsultationFirstException e) {
            Popups.showErrorMessage(e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rendez-vous-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rendez vous");
        stage.setScene(scene);




    }

}
