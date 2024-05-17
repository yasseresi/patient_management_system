package DataBases;

import Exceptions.UniqueUsernameViolationException;
import Exceptions.UserDoesNotExistException;
import Models.Orthophoniste.OrthophonisteSchema;

import java.io.Serializable;
import java.util.TreeMap;

public class OrthophonisteFileDataBase implements OrthophonistheDataBase, Serializable {
    private TreeMap<String, OrthophonisteSchema> orthophonistes = new TreeMap<>();

    public OrthophonisteFileDataBase() {
    }


    public OrthophonisteFileDataBase(TreeMap<String, OrthophonisteSchema> orthophonistes) {
        this.orthophonistes = orthophonistes;
    }


    @Override
    public void create(String username, String password) throws UniqueUsernameViolationException {
       if(orthophonistes.containsKey(username)) throw new UniqueUsernameViolationException();
       orthophonistes.put(username, new OrthophonisteSchema(username, password));
        orthophonistes.get(username);
    }

    @Override
    public OrthophonisteSchema create(OrthophonisteSchema newUser) throws UniqueUsernameViolationException {
        if (orthophonistes.containsKey(newUser.getNom()+" "+newUser.getPrenom())) throw new UniqueUsernameViolationException();
        orthophonistes.put(newUser.getNom()+" "+newUser.getPrenom(), newUser);
        return orthophonistes.get(newUser.getNom());
    }

    @Override
    public boolean exists(String username) {
        return orthophonistes.containsKey(username);
    }

    @Override
    public OrthophonisteSchema find(String username) throws UserDoesNotExistException {
        if (!orthophonistes.containsKey(username)) throw new UserDoesNotExistException();
        return orthophonistes.get(username);
    }

    @Override
    public OrthophonisteSchema update(String oldUsername, OrthophonisteSchema userSchema) throws UniqueUsernameViolationException, UserDoesNotExistException {
        if (!orthophonistes.containsKey(oldUsername)) throw new UserDoesNotExistException();
        String key = userSchema.getNom()+" "+userSchema.getPrenom();
        if (key.equals(oldUsername)) {
            return orthophonistes.replace(oldUsername, userSchema);
        } else {
            // In case he changes his username
            if (orthophonistes.containsKey(key)) throw new UniqueUsernameViolationException();
            orthophonistes.remove(oldUsername);
            return orthophonistes.put(key, userSchema);
        }
    }

    @Override
    public OrthophonisteSchema update(OrthophonisteSchema userSchema) throws UserDoesNotExistException {
        String key = userSchema.getNom()+" "+userSchema.getPrenom();
        if (!orthophonistes.containsKey(key)) throw new UserDoesNotExistException();
        return orthophonistes.replace(key, userSchema);
    }

    @Override
    public OrthophonisteSchema delete(String username) throws UserDoesNotExistException {
        if (!orthophonistes.containsKey(username)) throw new UserDoesNotExistException();
        return orthophonistes.remove(username);
}
}


