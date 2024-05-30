package Exceptions;

public class AllInputsShouldBeProvidedException  extends Exception{

    @Override
    public String getMessage(){
        return "Tous les champs doivent être remplis";
    }

}
