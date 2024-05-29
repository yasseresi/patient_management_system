package DataBases;

import Exceptions.UniqueUsernameViolationException;
import Exceptions.UserDoesNotExistException;
import Models.Orthophoniste.OrthophonisteSchema;

import java.io.Serializable;

public interface OrthophonistheDataBase extends Serializable {

    public void create(String username, String password) throws UniqueUsernameViolationException;
    public OrthophonisteSchema create(OrthophonisteSchema newUser) throws UniqueUsernameViolationException;
    public boolean exists(String username);
    public OrthophonisteSchema find(String username) throws UserDoesNotExistException;
    public OrthophonisteSchema update(String oldUsername,OrthophonisteSchema userSchema) throws UniqueUsernameViolationException, UserDoesNotExistException;
    public OrthophonisteSchema update(OrthophonisteSchema userSchema) throws UserDoesNotExistException;
    public OrthophonisteSchema udpateNbPhone(String username,int nbPhone);
    public OrthophonisteSchema updateAdress(String username , String adress);
    public OrthophonisteSchema updatePassword(String username,String password);
    public OrthophonisteSchema delete(String username) throws UserDoesNotExistException;

}
