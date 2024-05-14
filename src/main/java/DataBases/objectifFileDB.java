package DataBases;

import Models.Objectif.ObjectifShema;

import java.util.ArrayList;

public class objectifFileDB implements ObjectifDB{
    private ArrayList<ObjectifShema> objectifs ;

    public objectifFileDB(ArrayList<ObjectifShema> objectifs) {
        this.objectifs = objectifs;
    }

    public objectifFileDB(){
        this.objectifs  = new ArrayList<ObjectifShema>();
    }
}
