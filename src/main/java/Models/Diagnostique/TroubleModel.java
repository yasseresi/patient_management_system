package Models.Diagnostique;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

import static com.example.patient_management_system.HelloApplication.troubleDBFileName;

public class TroubleModel {

    TreeMap<String, TroubleSchema> troubles;

    public TroubleModel() {
        this.troubles = new TreeMap<>(); // Initialize the map
    }

    public TroubleModel(TreeMap<String, TroubleSchema> troubles) {
        this.troubles = troubles;
    }

    public void addTrouble(TroubleSchema trouble) {
        troubles.put(trouble.getNom(), trouble);
    }

    public void addTroubles(String nom, TypeTrouble categorie) {
        troubles.put(nom, new TroubleSchema(nom, categorie));
    }

    public void removeTrouble(TroubleSchema trouble) {
        troubles.remove(trouble.getNom());
    }

    public void updateTrouble(TroubleSchema oldTrouble, TroubleSchema newTrouble) {
        if (troubles.containsKey(oldTrouble.getNom())) {
            troubles.put(newTrouble.getNom(), newTrouble);
        }
    }

    // Save method to save troubles to a file
    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(troubleDBFileName))) {
            objectOutputStream.writeObject(troubles);
        }
    }

    // Load method to load troubles from a file
    public void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(troubleDBFileName))) {
            troubles = (TreeMap<String, TroubleSchema>) objectInputStream.readObject();
            System.out.println("loading the troubles model");
        }
    }

    public TreeMap<String, TroubleSchema> getTroubles() {
        return troubles;
    }



    public int getTroublebyType(TypeTrouble trouble){
        int count =0;
         for (Map.Entry<String,TroubleSchema> entry : troubles.entrySet()){
                TroubleSchema troubleSchema = entry.getValue();
                if (troubleSchema.getCategorie().equals(trouble)) ++count;
         }
         return count;
    }
}