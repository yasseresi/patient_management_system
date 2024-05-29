package Models.RendezVous;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

public abstract class RendezVousSchema implements Serializable , Comparator<RendezVousSchema> {


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
    public int compare(RendezVousSchema r1,RendezVousSchema r2){
        if(r1.getDate().isBefore(r2.getDate())) return 1;
        else if (r1.getDate().isEqual(r2.getDate())) return 0;
        else return -1;
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
