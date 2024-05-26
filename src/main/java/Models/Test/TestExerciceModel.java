package Models.Test;

import DataBases.PatientDB;
import Models.Question.QuestionEpreuve;
import com.example.patient_management_system.HelloApplication;

import java.io.*;
import java.util.ArrayList;

import static com.example.patient_management_system.HelloApplication.patientsDBFileName;

public class TestExerciceModel implements Serializable{

    private ArrayList<Exercice> exercices = new ArrayList<>();

    public TestExerciceModel() {
    }


    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+"listTestExercice.dt"))){
            objectOutputStream.writeObject(exercices);
            exercices.clear();
        }
    }

    public void load() throws IOException ,ClassNotFoundException{
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+"listTestExercice.dt"))) {
            exercices = (ArrayList<Exercice>) objectInputStream.readObject();
            System.out.println("loading the TestExerciseStatique model");
        } catch (EOFException e) {
            System.out.println("Error while loading Patient model: Unexpected end of file. The database file may be corrupted or incomplete.");
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }


    public void addExercice(Exercice exercice) {
        exercices.add(exercice);
    }

    public void removeExercice(Exercice exercice) {
        exercices.remove(exercice);
    }

    public void updateExercice(Exercice oldExercice, Exercice newExercice) {
        exercices.remove(oldExercice);
        exercices.add(newExercice);
    }

    public ArrayList<Exercice> getExercices() {
        return exercices;
    }

    public Exercice getExerciceByName(String name) {
        for (Exercice exercice : exercices) {
            if (exercice.getNom().equals(name)) {
                return exercice;
            }
        }
        return null;
    }
}
