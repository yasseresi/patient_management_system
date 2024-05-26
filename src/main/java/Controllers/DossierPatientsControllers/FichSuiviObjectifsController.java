package Controllers.DossierPatientsControllers;

import Models.Objectif.FichSuiviSchema;
import Models.Objectif.ObjectifModel;
import Models.Objectif.ObjectifShema;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class FichSuiviObjectifsController {

    @FXML
    private VBox chartContainer;

    public void setFichSuivi(FichSuiviSchema fichSuivi) {
        ArrayList<ObjectifShema> objectifShemas = convertToObjectifShemaList(fichSuivi.getObjectiflist());
        displayCharts(objectifShemas);
    }

    private ArrayList<ObjectifShema> convertToObjectifShemaList(ArrayList<ObjectifModel> objectifModels) {
        ArrayList<ObjectifShema> objectifShemas = new ArrayList<>();
        for (ObjectifModel model : objectifModels) {
            ArrayList<ObjectifShema> completeObjectives = model.findComplete();
            if (!completeObjectives.isEmpty()) {
                ObjectifShema firstObjective = completeObjectives.get(0); // Assuming we only need the first objective
                SortedMap<LocalDate, Integer> notes = new TreeMap<>(firstObjective.getNotes());
                ObjectifShema objectifShema = new ObjectifShema(firstObjective.getNom(), notes, firstObjective.getType());
                objectifShemas.add(objectifShema);
            }
        }
        return objectifShemas;
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
}