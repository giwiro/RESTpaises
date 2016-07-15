package app.models.mysql;


public class Departamento {

    private int id;
    private String nombre;
    private int id_pais;

    public Departamento(int id, String nombre, int id_pais) {
        this.id = id;
        this.nombre = nombre;
        this.id_pais = id_pais;
    }

    public Departamento() {

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

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public app.models.mongo.Departamento toDepartamentoMongo() {
        app.models.mongo.Departamento tmp = new app.models.mongo.Departamento();
        tmp.setNombre(this.getNombre());
        tmp.set_id(this.getId()+"");
        return tmp;
    }
}
