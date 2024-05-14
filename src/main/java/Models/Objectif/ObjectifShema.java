package Models.Objectif;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ObjectifShema {

   private  String nom;

   //sorted map can help finding the last note directly which help checking if the objectif is acheived or not
    private SortedMap<LocalDate, Integer> notes;

    private TypeObjectif type;


    public ObjectifShema(String nom, SortedMap<LocalDate, Integer> notes,TypeObjectif type) {
        this.nom = nom;
        this.notes = notes;
        this.type = type;
    }


    public ObjectifShema(String nom, TypeObjectif type) {
         this.nom = nom;
         this.type = type;
         notes = null;
    }

    public TypeObjectif getType() {
        return type;
    }

    public void setType(TypeObjectif type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public SortedMap<LocalDate, Integer> getNotes() {
        return notes;
    }

    public void setNotes(SortedMap<LocalDate, Integer> notes) {
        this.notes = notes;
    }
}
