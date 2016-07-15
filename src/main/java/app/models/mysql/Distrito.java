package app.models.mysql;


public class Distrito {
    private int id;
    private String nombre;
    private int poblacion;
    private int id_provincia;

    public Distrito() {
    }

    public Distrito(int id, String nombre, int poblacion, int id_provincia) {
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.id_provincia = id_provincia;
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

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public int getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(int id_provincia) {
        this.id_provincia = id_provincia;
    }
}
