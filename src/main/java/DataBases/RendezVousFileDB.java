package DataBases;

import Models.RendezVous.RendezVousSchema;

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
    public void create(LocalDate date, LocalTime time, RendezVousSchema rendezVous) {
        this.rendezVous.put(new TreeMap<LocalDate, LocalTime>(), rendezVous);
    }

    @Override
    public RendezVousSchema find(LocalDate date, LocalTime time) {
        return rendezVous.get(new TreeMap<LocalDate, LocalTime>());

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
