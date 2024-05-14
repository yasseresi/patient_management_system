package DataBases;

import Models.Objectif.ObjectifShema;

import java.util.ArrayList;

public class objectifDBFile implements ObjectifDB{
    private ArrayList<ObjectifShema> objectifs ;

    public objectifDBFile(ArrayList<ObjectifShema> objectifs) {
        this.objectifs = objectifs;
    }

    public objectifDBFile(){
        this.objectifs  = new ArrayList<ObjectifShema>();
    }
}
