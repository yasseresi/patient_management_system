package Models.RendezVous;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Objects;

public abstract class RendezVousSchema implements Serializable, Comparator<RendezVousSchema> {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RendezVousSchema that = (RendezVousSchema) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(duree, that.duree) &&
                Objects.equals(heure, that.heure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, duree, heure);
    }

    @Override
    public int compare(RendezVousSchema r1, RendezVousSchema r2) {
        int dateComparison = r1.getDate().compareTo(r2.getDate());
        if (dateComparison != 0) {
            return dateComparison;
        }
        return r1.getHeure().compareTo(r2.getHeure());
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