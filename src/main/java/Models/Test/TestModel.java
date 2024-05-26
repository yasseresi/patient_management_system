package Models.Test;

import DataBases.TestDB;
import Exceptions.TestDoesNotExistException;
import Exceptions.TestNomUniqueException;
import com.example.patient_management_system.HelloApplication;

import java.io.*;
import java.util.ArrayList;

public class TestModel {

    TestDB dataBase;

    public TestModel(TestDB dataBase) {
        this.dataBase = dataBase;
    }

    public TestModel() {
    }

    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.testsDBFileName))) {
            objectOutputStream.writeObject(dataBase);
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.testsDBFileName))) {
            dataBase = (TestDB) objectInputStream.readObject();
            System.out.println("loading the test model");
        }
    }

    public void createTest(TestSchema test) throws TestNomUniqueException {
        dataBase.createTest(test);
    }

    public void updateTest(TestSchema oldTest, TestSchema newTest) throws TestDoesNotExistException {
        dataBase.updateTest(oldTest, newTest);
    }

    // Method to get all tests
    public ArrayList<TestSchema> getAllTests() {
        return dataBase.getTests();
    }
}
