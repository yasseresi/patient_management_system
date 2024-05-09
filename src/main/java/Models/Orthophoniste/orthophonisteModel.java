package Models.Orthophoniste;

import DataBases.OrthophonistheDataBase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class orthophonisteModel {
    private OrthophonistheDataBase dataBase;

    public orthophonisteModel(OrthophonistheDataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void save() throws IOException{
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream())){

        }
    }


}
