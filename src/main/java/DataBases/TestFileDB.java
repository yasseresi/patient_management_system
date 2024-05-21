package DataBases;

import Exceptions.TestDoesNotExistException;
import Exceptions.TestNomUniqueException;
import Models.Question.QuestionEpreuve;
import Models.Test.TestSchema;

import java.util.TreeMap;

public class TestFileDB implements TestDB{
    TreeMap<String, TestSchema> tests ;

    public TestFileDB(){
        tests = new TreeMap<String, TestSchema>();
    }

    public TestFileDB(TreeMap<String, TestSchema> tests){
        this.tests = tests;
    }

    @Override
    public void createTest( TestSchema test) throws TestNomUniqueException{
        if(tests.containsKey(test.getNom())) throw new TestNomUniqueException();
        tests.put(test.getNom(), test);

    }

    @Override
    public void updateTest(TestSchema oldTest, TestSchema newTest) throws TestDoesNotExistException {
        if(!tests.containsKey(oldTest.getNom())) throw new TestDoesNotExistException();
        tests.remove(oldTest.getNom());
        tests.put(newTest.getNom(), newTest);
    }

    //todo: add methods to add or update exercices or change exercice's score


}
