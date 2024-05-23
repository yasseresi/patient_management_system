package Models.Diagnostique;

import java.io.*;
import java.util.List;
import java.util.TreeMap;

import static com.example.patient_management_system.HelloApplication.observationCliniqueDBFileName;
import static com.example.patient_management_system.HelloApplication.troubleDBFileName;

public class TroubleModel {

    TreeMap<String, TroubleSchema> troubles;

    public TroubleModel() {

    }

//    public void save() throws IOException {
//        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(troubleDBFileName))){
//            objectOutputStream.writeObject(troubles);
//        }
//    }
//
//    public void load() throws IOException, ClassNotFoundException {
//        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(troubleDBFileName))) {
//
//            troubles = (TreeMap<String, TroubleSchema>) objectInputStream.readObject();
//            System.out.println("loading the troubles model");
//        }
//    }

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
        troubles.remove(trouble);
    }


    public void updateTrouble(TroubleSchema oldTrouble, TroubleSchema newTrouble) {
        if (troubles.containsKey(newTrouble.getNom()) && oldTrouble.getNom().equals(newTrouble.getNom())) {
            troubles.remove(oldTrouble.getNom());
            troubles.put(newTrouble.getNom(), newTrouble);
        }
    }


}
