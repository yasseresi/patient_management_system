package DataBases;

import Exceptions.ConsultationAlreadyCreatedExecption;
import Exceptions.ConsultationFirstException;
import Models.RendezVous.AtelierSchema;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.RendezVousSchema;
import Models.RendezVous.SuiviSchema;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface RendezVouzDB extends Serializable {


    public void createConsult(LocalDate date, LocalTime time, ConsultationSchema rendezVous) throws ConsultationAlreadyCreatedExecption;
    public void createSuivi(LocalDate date,LocalTime time , SuiviSchema rendezVous) throws ConsultationFirstException;
    public void createAtelier( AtelierSchema rendezVous);
    RendezVousSchema find(LocalDate date, LocalTime time);

    ArrayList<RendezVousSchema> findAll(LocalDate date);

    ArrayList<RendezVousSchema> findAll();
}
