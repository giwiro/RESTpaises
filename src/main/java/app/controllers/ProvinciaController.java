package app.controllers;

import app.Application;
import app.DAO.mysql.ProvinciaDAO;
import app.models.Provincia;
import com.google.gson.Gson;
import spark.Route;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class ProvinciaController {

    static ProvinciaDAO provinciaDAO = new ProvinciaDAO(Application.mysqlConnection);

    public static Route handleReadProvincias = (request, response) -> {
        List<Provincia> provinciaList = provinciaDAO.getAllProvincias();
        String json = new Gson().toJson(provinciaList);
        return json;
    };

    public static Route handleCreateProvincia = (request, response) -> {
        boolean rpta = false;
        String nombre = request.queryParams("nombre");
        int id_departamento;
        try{
            id_departamento = Integer.parseInt(request.queryParams("id_departamento"));
            rpta = provinciaDAO.createProvincia(nombre, id_departamento);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }

        return rpta;
    };

    public static Route handleUpdateProvincia = (request, response) -> {
        boolean rpta = false;
        String nombre = request.queryParams("nombre");
        int id;
        int id_departamento;

        try{
            id_departamento = Integer.parseInt(request.queryParams("id_departamento"));
            id = Integer.parseInt(request.queryParams("id"));
            rpta = provinciaDAO.updateProvincia(id, nombre, id_departamento);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }
        return rpta;
    };

    public static Route handleDeleteProvincia = (request, response) -> {
        boolean rpta = false;
        int id;

        try{
            id = Integer.parseInt(request.queryParams("id"));
            rpta = provinciaDAO.deleteProvincia(id);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }
        return rpta;
    };
}
