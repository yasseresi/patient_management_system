package Models.Objectif;

import java.util.ArrayList;

public class FichSuiviModel {

    private ArrayList<FichSuiviSchema> fichsuivis ;

    public FichSuiviModel(ArrayList<FichSuiviSchema> fichsuivis) {
        this.fichsuivis = fichsuivis;
    }
    public FichSuiviModel(){}

    public ArrayList<FichSuiviSchema> getFichsuivis() {
        return fichsuivis;
    }

    public void setFichsuivis(ArrayList<FichSuiviSchema> fichsuivis) {
        this.fichsuivis = fichsuivis;
    }

}
