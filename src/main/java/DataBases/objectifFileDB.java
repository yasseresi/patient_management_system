package DataBases;

import Models.Objectif.ObjectifShema;
import Models.Objectif.TypeObjectif;

import java.time.LocalDate;
import java.util.ArrayList;

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
        return null;
    }

    @Override
    public ArrayList<ObjectifShema> findType(TypeObjectif type) {
        return null;
    }

    @Override
    public ObjectifShema create(String nom) {
        return null;
    }

    @Override
    public ObjectifShema addNote(String nom, LocalDate date, int note) {
        return null;
    }

    @Override
    public ObjectifShema addNote(ObjectifShema objectif, LocalDate date, int note) {
        return null;
    }

    @Override
    public ObjectifShema delete(String nom) {
        return null;
    }

    @Override
    public ArrayList<ObjectifShema> findComplete() {
        return null;
    }
}
