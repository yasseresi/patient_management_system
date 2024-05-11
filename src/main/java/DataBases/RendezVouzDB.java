package DataBases;

import Models.RendezVous.RendezVousSchema;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface RendezVouzDB extends Serializable {

    void create(LocalDate date, LocalTime time, RendezVousSchema rendezVous);

    RendezVousSchema find(LocalDate date, LocalTime time);

    ArrayList<RendezVousSchema> findAll(LocalDate date);
}
