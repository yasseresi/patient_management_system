package DataBases;

import Models.Objectif.ObjectifShema;
import Models.Objectif.TypeObjectif;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ObjectifDB extends Serializable {

    ObjectifShema find(String nom);
    ArrayList<ObjectifShema> findType(TypeObjectif type);
    ObjectifShema create(String nom , TypeObjectif type);

    ObjectifShema addNote(String nom, LocalDate date , int note);
    ObjectifShema addNote( ObjectifShema objectif, LocalDate date , int note);
    ObjectifShema delete(String nom);
    ArrayList<ObjectifShema> findComplete();


}
