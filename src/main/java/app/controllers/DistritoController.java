package app.controllers;

import app.Application;
import app.DAO.mysql.DistritoDAO;
import app.models.Distrito;
import com.google.gson.Gson;
import spark.Route;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class DistritoController {

    static DistritoDAO distritoDAO = new DistritoDAO(Application.mysqlConnection);

    public static Route handleReadDistritos = (request, response) -> {
        List<Distrito> departamentoList = distritoDAO.getAllDistritos();
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
            rpta = distritoDAO.createDistrito(nombre, poblacion, id_provincia);
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
            rpta = distritoDAO.updateDistrito(id, nombre, poblacion, id_provincia);
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
            rpta = distritoDAO.deleteDistrito(id);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }
        return rpta;
    };
}
