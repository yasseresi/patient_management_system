package DataBases;

import DataBases.DateTimeKey;
import DataBases.RendezVouzDB;
import Exceptions.ConsultationAlreadyPassedExecption;
import Exceptions.ConsultationFirstException;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.RendezVousSchema;
import Models.RendezVous.SuiviSchema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class RendezVousFileDB implements RendezVouzDB {
    TreeMap<DateTimeKey, RendezVousSchema> rendezVous;

    public RendezVousFileDB(TreeMap<DateTimeKey, RendezVousSchema> rendezVous) {
        this.rendezVous = rendezVous;
    }

    public RendezVousFileDB() {
        rendezVous = new TreeMap<>();

    }

    @Override
    public void create(LocalDate date, LocalTime time, RendezVousSchema rendezVous) throws ConsultationAlreadyPassedExecption, ConsultationFirstException {

        boolean isConsultationPassed = isConsultationAlreadyCreated(this.rendezVous);

        if (rendezVous instanceof ConsultationSchema && isConsultationPassed) throw new ConsultationAlreadyPassedExecption();
        if (rendezVous instanceof SuiviSchema && !isConsultationPassed) throw new ConsultationFirstException();
        DateTimeKey dateTimeKey = new DateTimeKey(date, time);
        this.rendezVous.put(dateTimeKey, rendezVous);
    }

    public void createConsult(LocalDate date, LocalTime time, ConsultationSchema rendezVous) throws ConsultationAlreadyPassedExecption{

        boolean isConsultationPassed = isConsultationAlreadyCreated(this.rendezVous);

        if (isConsultationPassed) throw new ConsultationAlreadyPassedExecption();
        DateTimeKey dateTimeKey = new DateTimeKey(date, time);
        this.rendezVous.put(dateTimeKey, rendezVous);
    }




    private boolean isConsultationAlreadyCreated(TreeMap<DateTimeKey, RendezVousSchema> rendezVous) {

        Iterator<RendezVousSchema> iterator = rendezVous.values().iterator();
        while (iterator.hasNext()) {
            RendezVousSchema key = iterator.next();
            if (key instanceof ConsultationSchema) {
                return true;
            }
        }
        return false;
    }

    @Override
    public RendezVousSchema find(LocalDate date, LocalTime time) {
        DateTimeKey key = new DateTimeKey(date, time);
        return rendezVous.get(key);
    }

    @Override
    public ArrayList<RendezVousSchema> findAll(LocalDate date) {
        ArrayList<RendezVousSchema> rendezVousList = new ArrayList<>();


        for (DateTimeKey key : rendezVous.keySet()) {
            if (date == null || key.getDate().equals(date)) {
                rendezVousList.add(rendezVous.get(key));
            }
        }
        return rendezVousList;
    }


    // New method to return all Rendezvous
    public ArrayList<RendezVousSchema> findAll() {
        return new ArrayList<>(rendezVous.values());
    }

}
