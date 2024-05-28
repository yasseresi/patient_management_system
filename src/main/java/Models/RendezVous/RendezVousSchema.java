package Models.RendezVous;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class RendezVousSchema implements Serializable ,Comparable<RendezVousSchema>{


    protected LocalDate date;
    protected LocalTime heure;
    protected String duree = "1h";
    protected String observation = "";


    public String getObservation() {
        return observation;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + heure.hashCode();
        result = 31 * result + duree.hashCode();
        return result;
    }


    //todo:modify this function to compaire between the rendezVous with date and time
    @Override
    public int compareTo(RendezVousSchema o) {
        return this.date.compareTo(o.date);
    }

    @Override
    public String toString() {
        return "RendezVousSchema{" +
                "date=" + date +
                ", heure=" + heure +
                ", duree=" + duree +
                ", observation='" + observation + '\'' +
                '}';
    }
}
