package app.models;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class Provincia {
    private int id;
    private String nombre;
    private int id_departamento;

    public Provincia() {
    }

    public Provincia(int id, String nombre, int id_departamento) {
        this.id = id;
        this.nombre = nombre;
        this.id_departamento = id_departamento;
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

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }
}
