package DataBases;

import Models.Objectif.ObjectifShema;
import Models.Objectif.TypeObjectif;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

public class objectifFileDB implements ObjectifDB{

    private ArrayList<ObjectifShema> objectifs ;

    public objectifFileDB(ArrayList<ObjectifShema> objectifs) {
        this.objectifs = objectifs;
    }

    public objectifFileDB(){
        this.objectifs  = new ArrayList<ObjectifShema>();
    }

    @Override
    public ObjectifShema find(String nom) {
        for (ObjectifShema objectif : objectifs) {
            if (objectif.getNom().equals(nom)) {
                return objectif;
            }
        }
        return null;
    }

    @Override
    public ArrayList<ObjectifShema> findType(TypeObjectif type) {
        ArrayList<ObjectifShema> result = new ArrayList<>();
        for (ObjectifShema objectif : objectifs) {
            if (objectif.getType() == type) {
                result.add(objectif);
            }
        }
        return result;
    }

    @Override
    public ObjectifShema create(String nom, TypeObjectif type) {
        ObjectifShema newObjectif = new ObjectifShema(nom, type);
        objectifs.add(newObjectif);
        return newObjectif;
    }

    @Override
    public ObjectifShema addNote(String nom, LocalDate date, int note) {
        ObjectifShema objectif = find(nom);
        if (objectif != null) {
            return addNote(objectif, date, note);
        }
        return null;
    }

    @Override
    public ObjectifShema addNote(ObjectifShema objectif, LocalDate date, int note) {
        SortedMap<LocalDate, Integer> notes = objectif.getNotes();
        if (notes == null) {
            notes = new TreeMap<>();
            objectif.setNotes(notes);
        }
        notes.put(date, note);
        return objectif;
    }

    @Override
    public ObjectifShema delete(String nom) {
        Optional<ObjectifShema> objectifOptional = objectifs.stream()
                .filter(objectif -> objectif.getNom().equals(nom))
                .findFirst();
        if (objectifOptional.isPresent()) {
            ObjectifShema objectif = objectifOptional.get();
            objectifs.remove(objectif);
            return objectif;
        }
        return null;
    }

//    @Override
//    public ArrayList<ObjectifShema> findComplete() {
//        return new ArrayList<>(objectifs);
//    }

    @Override
    public ArrayList<ObjectifShema> findComplete() {
        return objectifs;
    }
}
