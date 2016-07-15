package app.models.mysql;


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

    public app.models.mongo.Provincia toProvinciaMongo() {
        app.models.mongo.Provincia tmp = new app.models.mongo.Provincia();
        tmp.setNombre(this.getNombre());
        tmp.set_id(this.getId()+"");
        tmp.setId_departamento(this.getId_departamento());
        return tmp;
    }
}
