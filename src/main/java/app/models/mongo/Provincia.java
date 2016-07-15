package app.models.mongo;

/**
 * Created by Gi Wah Davalos on 15/07/2016.
 */
public class Provincia {

    private String _id;
    private String nombre;
    private int id_departamento;

    public Provincia() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
