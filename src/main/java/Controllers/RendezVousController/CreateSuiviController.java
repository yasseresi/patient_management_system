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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

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
    void CreerSuivi(ActionEvent event) {
        String nom = this.nom.getText();
        String prenom = this.prenom.getText();
        LocalDate date = rdvDate.getValue();
        int heure = rdvHeur.getValue();
        int min = rdvMin.getValue();
        PatientSchema patient;

        try {
            patient = HelloApplication.patientModel.find(nom, prenom);
            if(patient == null) throw new PatientDoesNotExistException();
            if (patient.isNouveau()) throw new ConsultationFirstException();
        } catch (PatientDoesNotExistException e) {
            Popups.showErrorMessage(e.getMessage());
            return;
        } catch (ConsultationFirstException e) {
            Popups.showErrorMessage(e.getMessage());
        }

        LocalTime time = LocalTime.of(heure, min);
        if (selectedType == null) {
            Popups.showErrorMessage("Please select the type of the follow-up (Suivi).");
            return;
        }


    }

}
