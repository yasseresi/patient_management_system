package Models.Bilan;

import java.util.ArrayList;

public class BilonModel {

    ArrayList<BilonSchema> bilans;
    

    public BilonModel() {
        this.bilans = new ArrayList<>();
    }

    public void addBilan(BilonSchema bilan) {
        this.bilans.add(bilan);
    }

    public ArrayList<BilonSchema> getBilans() {
        return bilans;
    }

    public void setBilans(ArrayList<BilonSchema> bilans) {
        this.bilans = bilans;
    }


}
