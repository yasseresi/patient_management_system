package Models.DossierPatient;

import Models.Bilan.BilonModel;
import Models.Bilan.BilonSchema;
import Models.Objectif.ObjectifModel;
import Models.RendezVous.RendezVousModel;

public class DossierPatientSchema {

    private String id;

    private RendezVousModel rendezVous;
    private ObjectifModel fichSuivi;
    private BilonModel bilan;


    public DossierPatientSchema(String id, RendezVousModel rendezVous, ObjectifModel fichSuivi, BilonModel bilan) {
        this.id = id;
        this.rendezVous = rendezVous;
        this.fichSuivi = fichSuivi;
        this.bilan = bilan;
    }

    public RendezVousModel getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(RendezVousModel rendezVous) {
        this.rendezVous = rendezVous;
    }

    public ObjectifModel getFichSuivi() {
        return fichSuivi;
    }

    public void setFichSuivi(ObjectifModel fichSuivi) {
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
}
