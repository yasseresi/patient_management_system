package Models.ObservationsCliniques;

public class ObservationSchema {

    String contenu;

    public ObservationSchema(String contenu) {
        this.contenu = contenu;
    }

    public ObservationSchema() {
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
