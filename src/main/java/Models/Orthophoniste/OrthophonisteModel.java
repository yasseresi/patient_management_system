package Models.Orthophoniste;

import DataBases.OrthophonistheDataBase;
import Exceptions.UniqueUsernameViolationException;
import Exceptions.UserDoesNotExistException;
import com.example.patient_management_system.HelloApplication;

import java.io.*;

import static com.example.patient_management_system.HelloApplication.*;

public class OrthophonisteModel {
    private OrthophonistheDataBase dataBase;

    public OrthophonisteModel(OrthophonistheDataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void save() throws IOException{
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.usersDirectoryName + "/"+ HelloApplication.TherapistDBuserName))){
            objectOutputStream.writeObject(dataBase);

        }
    }

    public void load() throws IOException, ClassNotFoundException {
        System.out.println(HelloApplication.usersDirectoryName + "/" + HelloApplication.TherapistDBuserName);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName + "/" + HelloApplication.TherapistDBuserName))) {

            dataBase = (OrthophonistheDataBase) objectInputStream.readObject();
            System.out.println("loading the orthophiste model");
        }
    }
    public boolean exists(String username){
        return this.dataBase.exists(username);
    }

    public OrthophonisteSchema create(OrthophonisteSchema userSchema) throws UniqueUsernameViolationException {
        return this.dataBase.create(userSchema);
    }
    public void create(String username, String password) throws UniqueUsernameViolationException {
        this.dataBase.create(username, password);
    }
    public OrthophonisteSchema find(String username) throws UserDoesNotExistException {
        return this.dataBase.find(username);
    }
    public OrthophonisteSchema update(String oldUsername,OrthophonisteSchema userSchema) throws UniqueUsernameViolationException,
            UserDoesNotExistException{
        return this.dataBase.update(oldUsername, userSchema);
    }
    public OrthophonisteSchema update(OrthophonisteSchema userSchema) throws UserDoesNotExistException{
        return this.dataBase.update(userSchema);
    }
    public OrthophonisteSchema delete(String username) throws UserDoesNotExistException{
        return this.dataBase.delete(username);
    }
    public OrthophonisteSchema updatePassword(String username,String password){
        return this.dataBase.updatePassword(username,password);
    }
    public OrthophonisteSchema updateAdress(String username, String adress){
        return this.dataBase.updateAdress(username,adress);
    }
    public OrthophonisteSchema updatePhoneNumber(String username,int nbPhone){
        return this.dataBase.udpateNbPhone(username, nbPhone);
    }

}
