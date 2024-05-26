package Models.Objectif;

import java.util.ArrayList;

public class FichSuiviSchema {

    private ArrayList<ObjectifModel> objectiflist ;

    public FichSuiviSchema(ArrayList<ObjectifModel> objectiflist) {
        this.objectiflist = objectiflist;
    }

    public ArrayList<ObjectifModel> getObjectiflist() {
        return objectiflist;
    }

    public void setObjectiflist(ArrayList<ObjectifModel> objectiflist) {
        this.objectiflist = objectiflist;
    }
}
