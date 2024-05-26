package Controllers.DossierPatientsControllers;

import Models.Objectif.ObjectifShema;
import Models.Objectif.TypeObjectif;
import Models.Patient.PatientSchema;
import Utils.SceneSwitcher;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class FicheSuiviController {

    @FXML
    private Label patientNameLabel;

    @FXML
    private VBox chartContainer;

    @FXML
    private ScrollPane scrollPane; // Add this line to reference the ScrollPane

    public void setPatientDetails(PatientSchema patient) {

        // ------------------ Example data for testing ------------------------------
        ArrayList<ObjectifShema> objectifs = new ArrayList<>();
        SortedMap<LocalDate, Integer> notes1 = new TreeMap<>();
        notes1.put(LocalDate.of(2023, 1, 1), 3);
        notes1.put(LocalDate.of(2023, 2, 1), 1);
        notes1.put(LocalDate.of(2023, 5, 1), 5);
        objectifs.add(new ObjectifShema("Objective 1", notes1, TypeObjectif.COURT_TERM));

        SortedMap<LocalDate, Integer> notes2 = new TreeMap<>();
        notes2.put(LocalDate.of(2023, 1, 15), 0);
        notes2.put(LocalDate.of(2023, 5, 15), 2);
        notes2.put(LocalDate.of(2023, 10, 15), 5);
        objectifs.add(new ObjectifShema("Objective 2", notes2, TypeObjectif.LONG_TERM));

        SortedMap<LocalDate, Integer> notes3 = new TreeMap<>();
        notes3.put(LocalDate.of(2023, 1, 10), 1);
        notes3.put(LocalDate.of(2023, 2, 10), 2);
        notes3.put(LocalDate.of(2023, 3, 10), 3);
        objectifs.add(new ObjectifShema("Objective 3", notes3, TypeObjectif.LONG_TERM));

        SortedMap<LocalDate, Integer> notes4 = new TreeMap<>();
        notes4.put(LocalDate.of(2023, 1, 10), 1);
        notes4.put(LocalDate.of(2023, 4, 10), 3);
        notes4.put(LocalDate.of(2023, 7, 10), 3);
        objectifs.add(new ObjectifShema("Objective 4", notes4, TypeObjectif.MOYEN_TERM));

        SortedMap<LocalDate, Integer> notes5 = new TreeMap<>();
        notes5.put(LocalDate.of(2023, 10, 10), 1);
        notes5.put(LocalDate.of(2023, 11, 10), 2);
        notes5.put(LocalDate.of(2023, 12, 10), 5);
        objectifs.add(new ObjectifShema("Objective 5", notes5, TypeObjectif.COURT_TERM));

        ///////////////////////////////////////////////////////////////////////////////////

        patientNameLabel.setText(patient.getNom().toUpperCase() + " " + patient.getPrenom().toUpperCase() + "   |   " + LocalDate.now());

        System.out.println("Current Patient: " + HelloApplication.currentPatientName);

        if (new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName, HelloApplication.fichSuivisDBFileName).exists()) {
            System.out.println("Objectif(fiche suivi) file exists");
            HelloApplication.fichesuivi.load();
            System.out.println("Objectif model has been loaded");
        }

        ArrayList<ObjectifShema> objectifsFromDB = HelloApplication.fichesuivi.findComplete();
        if (objectifsFromDB == null){
            System.out.println("Objectifs list is null");
            objectifsFromDB = new ArrayList<>(); // Initialize an empty list if null
        }

        objectifs.addAll(objectifsFromDB); // Combine example data with loaded data

        displayCharts(objectifs);
    }

    private void displayCharts(ArrayList<ObjectifShema> objectifs) {
        chartContainer.getChildren().clear(); // Clear any existing charts

        for (ObjectifShema objectif : objectifs) {
            LineChart<Number, Number> lineChart = createChartForObjective(objectif);
            chartContainer.getChildren().add(lineChart);
        }
    }

    private LineChart<Number, Number> createChartForObjective(ObjectifShema objectif) {
        NumberAxis xAxis = new NumberAxis(1, 12, 1); // Representing months from January to December
        NumberAxis yAxis = new NumberAxis(1, 5, 1); // Assuming notes are between 1 and 5

        yAxis.setLabel("Note sur 5");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(objectif.getNom());

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(objectif.getNom());

        // Assuming notes is a Map<LocalDate, Integer>
        SortedMap<LocalDate, Integer> notes = objectif.getNotes();
        notes.forEach((date, note) -> {
            int month = date.getMonthValue(); // Get the month value from the date
            series.getData().add(new XYChart.Data<>(month, note));
        });

        lineChart.getData().add(series);
        return lineChart;
    }

    @FXML
    private void handleBackToPatientDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "dossier-patient-view.fxml", 800, 600);
    }
}
