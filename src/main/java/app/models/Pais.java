package app.models;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class Pais {
    private int id;
    private String nombre;
    private int pbi;

    public Pais(int id, String nombre, int pbi) {
        this.id = id;
        this.nombre = nombre;
        this.pbi = pbi;
    }

    public Pais() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPbi() {
        return pbi;
    }

    public void setPbi(int pbi) {
        this.pbi = pbi;
    }
}
