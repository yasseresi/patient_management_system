package DataBases;

import Exceptions.TestDoesNotExistException;
import Exceptions.TestNomUniqueException;
import Models.Test.TestSchema;
import Models.Test.Exercice; // Import Exercise class


import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class TestFileDB implements TestDB, Serializable {
    TreeMap<String, TestSchema> tests ;


    public TestFileDB() {
        tests = new TreeMap<String, TestSchema>();
    }

    public TestFileDB(TreeMap<String, TestSchema> tests) {
        this.tests = tests;
    }

    @Override
    public void createTest(TestSchema test) throws TestNomUniqueException {
        if (tests.containsKey(test.getNom())) throw new TestNomUniqueException();
        tests.put(test.getNom(), test);
    }

    @Override
    public void updateTest(TestSchema oldTest, TestSchema newTest) throws TestDoesNotExistException {
        if (!tests.containsKey(oldTest.getNom())) throw new TestDoesNotExistException();
        tests.remove(oldTest.getNom());
        tests.put(newTest.getNom(), newTest);
    }

    @Override
    public ArrayList<TestSchema> getTests() {
        // Convert the values (tests) in the TreeMap to an ArrayList
        return new ArrayList<>(tests.values());
    }
//
//    // Method to add an exercise to a test
//    public void addExerciseToTest(String testNom, Exercice exercise) throws TestDoesNotExistException {
//        TestSchemaE test = tests.get(testNom);
//        if (test == null) {
//            throw new TestDoesNotExistException("Test not found: " + testNom);
//        }
//        test.addExercise(exercise); // Make sure TestSchema has an addExercise method
//    }
//
//    // Method to update an exercise in a test
//    public void updateExerciseInTest(String testNom, Exercice oldExercise, Exercice newExercise) throws TestDoesNotExistException {
//        TestSchema test = tests.get(testNom);
//        if (test == null) {
//            throw new TestDoesNotExistException("Test not found: " + testNom);
//        }
//        test.updateExercice(oldExercise, newExercise); // Make sure TestSchema has an updateExercise method
//    }
//
//    // Method to change the score of an exercise in a test
//    public void changeExerciseScore(String testNom, Exercice exercise, int newScore) throws TestDoesNotExistException {
//        TestSchema test = tests.get(testNom);
//        if (test == null) {
//            throw new TestDoesNotExistException("Test not found: " + testNom);
//        }
//        test.setExerciseScore(exercise, newScore); // Make sure TestSchema has a setExerciseScore method
//    }
}
