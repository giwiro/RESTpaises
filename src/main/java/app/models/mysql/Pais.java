package app.models.mysql;


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

    public app.models.mongo.Pais toPaisMongo() {
        app.models.mongo.Pais tmp = new app.models.mongo.Pais();
        tmp.setNombre(this.getNombre());
        tmp.setPbi(this.getPbi());
        tmp.set_id(this.getId()+"");
        return tmp;
    }
}
