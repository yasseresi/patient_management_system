package DataBases;

import Exceptions.TestDoesNotExistException;
import Exceptions.TestNomUniqueException;
import Models.Test.TestSchema;

public interface TestDB {


    public void createTest(TestSchema test) throws TestNomUniqueException;

    public void updateTest(TestSchema oldTest, TestSchema newTest) throws TestDoesNotExistException;
}
