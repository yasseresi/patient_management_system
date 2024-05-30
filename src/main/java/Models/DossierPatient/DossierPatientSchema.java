package Models.DossierPatient;

import Models.Bilan.BilonModel;
import Models.Bilan.BilonSchema;
import Models.Diagnostique.TypeTrouble;
import Models.Objectif.FichSuiviModel;
import Models.Objectif.ObjectifModel;
import Models.RendezVous.RendezVousModel;

import java.util.ArrayList;

public class DossierPatientSchema {

    private String id;

    private RendezVousModel rendezVous;
    private FichSuiviModel fichSuivi;
    private BilonModel bilan;


    public DossierPatientSchema(String id, RendezVousModel rendezVous, FichSuiviModel fichSuivi, BilonModel bilan) {
        this.id = id;
        this.rendezVous = rendezVous;
        this.fichSuivi = fichSuivi;
        this.bilan = bilan;
    }
    public DossierPatientSchema(String id){
        this.id = id;
        this.bilan = new BilonModel();
        this.fichSuivi = new FichSuiviModel(new ArrayList<>());
        this.rendezVous = new RendezVousModel();
    }

    public RendezVousModel getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(RendezVousModel rendezVous) {
        this.rendezVous = rendezVous;
    }

    public FichSuiviModel getFichSuivi() {
        return fichSuivi;
    }

    public void setFichSuivi(FichSuiviModel fichSuivi) {
        this.fichSuivi = fichSuivi;
    }

    public BilonModel getBilan() {
        return bilan;
    }

    public void setBilan(BilonModel bilan) {
        this.bilan = bilan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DossierPatientSchema{" +
                "id='" + id + '\'' +
                '}';
    }

    public int getNbTroubleType(TypeTrouble typeTrouble){
        return this.bilan.getNbTroubleType(typeTrouble);
    }
}
