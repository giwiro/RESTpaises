package app.controllers;

import app.Application;
import app.DAO.mysql.DistritoSqlDAO;
import app.models.mysql.Distrito;
import com.google.gson.Gson;
import spark.Route;

import java.sql.SQLException;
import java.util.List;


public class DistritoController {

    static DistritoSqlDAO distritoSqlDAO = new DistritoSqlDAO(Application.mysqlConnection);

    public static Route handleReadDistritos = (request, response) -> {
        List<Distrito> departamentoList = distritoSqlDAO.getAllDistritos();
        String json = new Gson().toJson(departamentoList);
        return json;
    };

    public static Route handleCreateDistrito = (request, response) -> {
        boolean rpta = false;
        String nombre = request.queryParams("nombre");
        int poblacion;
        int id_provincia;
        try{
            poblacion = Integer.parseInt(request.queryParams("poblacion"));
            id_provincia = Integer.parseInt(request.queryParams("id_provincia"));
            rpta = distritoSqlDAO.createDistrito(nombre, poblacion, id_provincia);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }

        return rpta;
    };

    public static Route handleUpdateDistrito = (request, response) -> {
        boolean rpta = false;
        String nombre = request.queryParams("nombre");
        int poblacion;
        int id_provincia;
        int id;

        try{
            poblacion = Integer.parseInt(request.queryParams("poblacion"));
            id_provincia = Integer.parseInt(request.queryParams("id_provincia"));
            id = Integer.parseInt(request.queryParams("id"));
            rpta = distritoSqlDAO.updateDistrito(id, nombre, poblacion, id_provincia);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }
        return rpta;
    };

    public static Route handleDeleteDistrito = (request, response) -> {
        boolean rpta = false;
        int id;

        try{
            id = Integer.parseInt(request.queryParams("id"));
            rpta = distritoSqlDAO.deleteDistrito(id);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }
        return rpta;
    };
}
