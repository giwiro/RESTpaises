package app.controllers;

import app.Application;
import app.DAO.mongo.DepartamentoMongoDAO;
import app.DAO.mongo.PaisMongoDAO;
import app.DAO.mongo.ProvinciaMongoDAO;
import app.DAO.mysql.DepartamentoSqlDAO;
import app.DAO.mysql.DistritoSqlDAO;
import app.DAO.mysql.PaisSqlDAO;
import app.DAO.mysql.ProvinciaSqlDAO;
import app.models.mysql.Departamento;
import app.models.mysql.Distrito;
import app.models.mysql.Pais;
import app.models.mysql.Provincia;
import org.bson.Document;
import spark.Route;

import java.util.List;


public class GenerarDocumentosController {

    static PaisSqlDAO paisSqlDAO = new PaisSqlDAO(Application.mysqlConnection);
    static DepartamentoSqlDAO departamentoSqlDAO = new DepartamentoSqlDAO(Application.mysqlConnection);
    static ProvinciaSqlDAO provinciaSqlDAO = new ProvinciaSqlDAO(Application.mysqlConnection);
    static DistritoSqlDAO distritoSqlDAO = new DistritoSqlDAO(Application.mysqlConnection);

    static PaisMongoDAO paisMongoDAO= new PaisMongoDAO(Application.mongoDB);
    static DepartamentoMongoDAO departamentoMongoDAO = new DepartamentoMongoDAO(Application.mongoDB);
    static ProvinciaMongoDAO provinciaMongoDAO = new ProvinciaMongoDAO(Application.mongoDB);

    public static Route handleGenerateDoc = (request, response) -> {
        boolean rpta = false;
        int id_pais;
        int id_provincia;
        System.out.println("incomming generate doc");
        try{
            id_pais = Integer.parseInt(request.queryParams("id_pais"));
            id_provincia = Integer.parseInt(request.queryParams("id_provincia"));
        }catch(NumberFormatException e){
            return rpta;
        }

        Pais pais = paisSqlDAO.getPais(id_pais);
        Departamento departamento = departamentoSqlDAO.readDepartamentoByProvincia(id_provincia);
        Provincia provincia = provinciaSqlDAO.getProvincia(id_provincia);

        if (pais == null) {
            System.out.println("No hay pais");
            return rpta;
        }

        if (departamento == null) {
            System.out.println("No hay departamento");
            return rpta;
        }

        if (provincia == null) {
            System.out.println("No hay provincia");
            return rpta;
        }

        /*System.out.println("Found pais: " + pais.getNombre());
        System.out.println("Found departamento: " + departamento.getNombre());*/
        List<Distrito> distritos = distritoSqlDAO.getDistritosByProvincia(provincia.getId());
        System.out.println("found " + distritos.size() + " distritos, para el idProvincia: " + provincia.getId());

        app.models.mongo.Pais paisMongo = pais.toPaisMongo();
        app.models.mongo.Departamento departamentoMongo = departamento.toDepartamentoMongo();
        app.models.mongo.Provincia provinciaMongo = provincia.toProvinciaMongo();

        Document paisDoc = paisMongoDAO.insertPais(paisMongo);
        Document depaDoc = departamentoMongoDAO.insertDepartamento(paisDoc, departamentoMongo);
        Document provDoc = provinciaMongoDAO.insertProvincia(depaDoc, provinciaMongo, distritos);

        rpta = true;


        return rpta;
    };

}
