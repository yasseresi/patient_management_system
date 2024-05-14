package Models.ObservationsCliniques;



import java.io.*;
import java.util.List;

import static com.example.patient_management_system.HelloApplication.observationCliniqueDBFileName;

public class ObservationModel {
       List<String> observationsDB;


         public ObservationModel(List<String> observationsDB) {
              this.observationsDB = observationsDB;
         }


       public ObservationModel(){

       }


    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(observationCliniqueDBFileName))){
            objectOutputStream.writeObject(observationsDB);
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(observationCliniqueDBFileName))) {

            observationsDB = (List<String>) objectInputStream.readObject();
            System.out.println("loading the observations clinique model");
        }
    }

}
