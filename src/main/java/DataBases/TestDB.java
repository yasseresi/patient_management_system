package DataBases;

import Exceptions.TestDoesNotExistException;
import Exceptions.TestNomUniqueException;
import Models.Test.TestSchema;

import java.util.ArrayList;

public interface TestDB {

    public void createTest(TestSchema test) throws TestNomUniqueException;

    public void updateTest(TestSchema oldTest, TestSchema newTest) throws TestDoesNotExistException;

    ArrayList<TestSchema> getTests();
}
