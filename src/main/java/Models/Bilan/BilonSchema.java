package Models.Bilan;

import Models.Anamnese.AnamneseModel;
import Models.Diagnostique.TroubleModel;
import Models.Diagnostique.TypeTrouble;
import Models.ObservationsCliniques.ObservationModel;
import Models.Test.TestModel;

public class BilonSchema {

    private AnamneseModel anamneseModel;

    private TestModel tests;

    private ObservationModel observations;

    private TroubleModel diagnostic;


    public BilonSchema(AnamneseModel anamneseModel, TestModel tests, ObservationModel observations, TroubleModel diagnostic) {
        this.anamneseModel = anamneseModel;
        this.tests = tests;
        this.observations = observations;
        this.diagnostic = diagnostic;
    }

    public BilonSchema() {

    }

    public AnamneseModel getAnamneseModel() {
        return anamneseModel;
    }

    public void setAnamneseModel(AnamneseModel anamneseModel) {
        this.anamneseModel = anamneseModel;
    }

    public TestModel getTests() {
        return tests;
    }

    public void setTests(TestModel tests) {
        this.tests = tests;
    }

    public ObservationModel getObservations() {
        return observations;
    }

    public void setObservations(ObservationModel observations) {
        this.observations = observations;
    }

    public TroubleModel getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(TroubleModel diagnostic) {
        this.diagnostic = diagnostic;
    }

    public int getNbTrouble(TypeTrouble trouble){
        return this.diagnostic.getTroublebyType(trouble);
    }
}
