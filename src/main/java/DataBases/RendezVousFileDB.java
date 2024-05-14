package DataBases;

import Exceptions.ConsultationAlreadyPassedExecption;
import Exceptions.ConsultationFirstException;
import Models.RendezVous.ConsultationSchema;
import Models.RendezVous.RendezVousSchema;
import Models.RendezVous.SuiviSchema;
import org.w3c.dom.html.HTMLImageElement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class RendezVousFileDB implements RendezVouzDB{
    TreeMap<TreeMap<LocalDate, LocalTime>,RendezVousSchema> rendezVous = new TreeMap<>();


    public RendezVousFileDB(TreeMap<TreeMap<LocalDate, LocalTime>, RendezVousSchema> rendezVous) {
        this.rendezVous = rendezVous;
    }

    public RendezVousFileDB() {
    }

    @Override
    public void create(LocalDate date, LocalTime time, RendezVousSchema rendezVous) throws ConsultationAlreadyPassedExecption, ConsultationFirstException {
        boolean isConsultationPassed = isConsultationAlreadyPassed(this.rendezVous);
        if(rendezVous instanceof ConsultationSchema && isConsultationPassed) throw new ConsultationAlreadyPassedExecption();
        if(rendezVous instanceof SuiviSchema && !isConsultationPassed) throw new ConsultationFirstException();
        this.rendezVous.put(new TreeMap<LocalDate, LocalTime>(), rendezVous);
    }


    private boolean isConsultationAlreadyPassed(TreeMap<TreeMap<LocalDate, LocalTime>,RendezVousSchema> rendezVous){
        Iterator<RendezVousSchema> iterator = rendezVous.values().iterator();
        while (iterator.hasNext()){
            RendezVousSchema key = iterator.next();
            if(key instanceof ConsultationSchema){
                return true;
            }
        }
        return false;
    }

    @Override
    public RendezVousSchema find(LocalDate date, LocalTime time) {
        TreeMap<LocalDate, LocalTime> key = null;
        key.put(date, time);
        return rendezVous.get(key);

    }

    @Override
    public ArrayList<RendezVousSchema> findAll(LocalDate date) {

        //TODO: CHECK LATER THE IMPLEMENTATION OF THE ITERATOR LOOP
        Iterator<TreeMap<LocalDate, LocalTime>> iterator = rendezVous.keySet().iterator();
        ArrayList<RendezVousSchema> rendezVousList = new ArrayList<>();
        while(iterator.hasNext()){
            TreeMap<LocalDate, LocalTime> key = iterator.next();
            if (key.containsKey(date)){
                rendezVousList.add(rendezVous.get(key));
            }
        }
        return rendezVousList;

    }
}
