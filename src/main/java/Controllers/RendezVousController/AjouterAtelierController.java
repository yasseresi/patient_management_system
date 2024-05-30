package Controllers.RendezVousController;

import Exceptions.AllInputsShouldBeProvidedException;
import Models.DossierPatient.DossierPatientSchema;
import Models.Patient.PatientSchema;
import Models.RendezVous.AtelierSchema;
import Utils.Popups;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AjouterAtelierController implements Initializable {

    @FXML
    private ListView<PatientSchema> listPatients;

    @FXML
    private DatePicker rdvDate;

    @FXML
    private ComboBox<Integer> rdvHeur;

    @FXML
    private ComboBox<Integer> rdvMin;

    @FXML
    private TextField tf_thematique;


    private final ObservableList<PatientSchema> patients = FXCollections.observableArrayList();
    private final List<PatientSchema> selectedPatients = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // loading patients
        // Load patients (this could be fetched from a database or other source)
        patients.addAll(HelloApplication.patientModel.getPatients());

        // Populate the hours ComboBox
        IntStream.range(0, 24).forEach(i -> rdvHeur.getItems().add(i));
        rdvHeur.getSelectionModel().selectFirst();

        // Populate the minutes ComboBox with intervals of 5 minutes
        IntStream.iterate(0, i -> i + 5).limit(12).forEach(i -> rdvMin.getItems().add(i));
        rdvMin.getSelectionModel().selectFirst();

        // Set the cell factory to display CheckBox and Labels for nom and prenom
        listPatients.setCellFactory(new Callback<>() {
            @Override
            public ListCell<PatientSchema> call(ListView<PatientSchema> listView) {
                return new ListCell<PatientSchema>() {
                    private final CheckBox checkBox = new CheckBox();

                    @Override
                    protected void updateItem(PatientSchema patient, boolean empty) {
                        super.updateItem(patient,empty);
                        if (empty || patient == null) {
                            setGraphic(null);
                        } else {
                            checkBox.setText(patient.getNom() + " " + patient.getPrenom());
                            checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                                if (isNowSelected) {
                                    selectedPatients.add(patient);
                                } else {
                                    selectedPatients.remove(patient);
                                }
                            });
                            setGraphic(checkBox);
                        }
                    }
                };
            }
        });

        listPatients.setItems(patients);
    }

    @FXML
    void handleAddButton(ActionEvent event) {
        for (PatientSchema patient : selectedPatients) {
            System.out.println(patient.getNom() + " " + patient.getPrenom());
        }

    }

    @FXML
    void handleCreateButton(ActionEvent event) throws IOException {
        try {
            // handle inputs validation
            if (tf_thematique.getText().isEmpty() || selectedPatients.isEmpty()) {
                throw new AllInputsShouldBeProvidedException();
            }

            // Print debug statements
            System.out.println("Hour ComboBox value: " + rdvHeur.getValue());
            System.out.println("Minute ComboBox value: " + rdvMin.getValue());

            // handle the creation of a Consultation and a new Patient
            LocalDate rdvDay = this.rdvDate.getValue();
            // Check if the selected values are integers
            Integer rdvHour = Integer.parseInt(String.valueOf(this.rdvHeur.getValue()));
            Integer rdvMinute = Integer.parseInt(String.valueOf(this.rdvMin.getValue()));
            System.out.println("the selected time is :" +rdvHour + " "+rdvMinute);


            if (rdvHour == null || rdvMinute == null) {
                throw new IllegalArgumentException("Please select valid time values.");
            }

            LocalTime rdvTime = LocalTime.of(rdvHour, rdvMinute);
            String thematique = tf_thematique.getText();
            ArrayList<String> listePatients = selectedPatients.stream()
                    .map(patient -> patient.getNom() + " " + patient.getPrenom())
                    .collect(Collectors.toCollection(ArrayList::new));

            for (int i = 0; i < listePatients.size(); i++) {
                System.out.println(listePatients.get(i));
            }

            // create new Atelier
            AtelierSchema newAtelier = new AtelierSchema(rdvDay, rdvTime, thematique, listePatients);

            HelloApplication.dossierPatientModel.CreerAtelier(newAtelier);

            Popups.showSuccessMessage("Created", "Atelier ajouté avec succès");
        } catch (AllInputsShouldBeProvidedException e) {
            Popups.showErrorMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            Popups.showErrorMessage(e.getMessage());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rendez-vous-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rendez vous");
        stage.setScene(scene);
    }


}
