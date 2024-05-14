package DataBases;

import Exceptions.ConsultationAlreadyPassedExecption;
import Exceptions.ConsultationFirstException;
import Models.RendezVous.RendezVousSchema;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface RendezVouzDB extends Serializable {

    void create(LocalDate date, LocalTime time, RendezVousSchema rendezVous) throws ConsultationAlreadyPassedExecption, ConsultationFirstException;

    RendezVousSchema find(LocalDate date, LocalTime time);

    ArrayList<RendezVousSchema> findAll(LocalDate date);
}
