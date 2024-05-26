package Models.RendezVous;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class RendezVousSchema implements Serializable {


    protected LocalDate date;
    protected LocalTime heure;
    protected Duration duree = Duration.ofHours(1);
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


    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + heure.hashCode();
        result = 31 * result + duree.hashCode();
        return result;
    }

}
