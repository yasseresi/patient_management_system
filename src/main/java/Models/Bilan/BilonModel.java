package Models.Bilan;

import DataBases.RendezVouzDB;
import com.example.patient_management_system.HelloApplication;

import java.io.*;
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

    // Load method to load bilans from a file
    public void load() throws IOException, ClassNotFoundException {
        File file = new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName, HelloApplication.boDBFileName);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                bilans = (ArrayList<BilonSchema>) ois.readObject();
            } catch (EOFException e) {
                // Handle the case where the file is empty
                System.out.println("The rendez_vous data file is empty.");
            } catch (IOException | ClassNotFoundException e) {
                // Handle any other IOException or ClassNotFoundException that might occur
                e.printStackTrace();
            }
        } else {
            bilans = new ArrayList<>(); // Initialize an empty list if file does not exist
        }
    }

    // Save method to save bilans to a file
    public void save() throws IOException {
        File file = new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName, HelloApplication.boDBFileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(bilans);
        }
    }


}
