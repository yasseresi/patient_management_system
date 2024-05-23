package Models.Bilan;

import Models.Anamnese.AnamneseModel;
import Models.ObservationsCliniques.ObservationModel;
import Models.Test.TestModel;

public class BilonSchema {

    private AnamneseModel anamneseModel;

    private TestModel tests;

    private ObservationModel observations;


    public BilonSchema(AnamneseModel anamneseModel, TestModel tests, ObservationModel observations) {
        this.anamneseModel = anamneseModel;
        this.tests = tests;
        this.observations = observations;
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


}
