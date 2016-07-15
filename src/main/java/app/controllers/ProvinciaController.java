package app.controllers;

import app.Application;
import app.DAO.mysql.ProvinciaSqlDAO;
import app.models.mysql.Provincia;
import com.google.gson.Gson;
import spark.Route;

import java.sql.SQLException;
import java.util.List;


public class ProvinciaController {

    static ProvinciaSqlDAO provinciaSqlDAO = new ProvinciaSqlDAO(Application.mysqlConnection);

    public static Route handleReadProvincias = (request, response) -> {
        List<Provincia> provinciaList = provinciaSqlDAO.getAllProvincias();
        String json = new Gson().toJson(provinciaList);
        return json;
    };

    public static Route handleCreateProvincia = (request, response) -> {
        boolean rpta = false;
        String nombre = request.queryParams("nombre");
        int id_departamento;
        try{
            id_departamento = Integer.parseInt(request.queryParams("id_departamento"));
            rpta = provinciaSqlDAO.createProvincia(nombre, id_departamento);
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
            rpta = provinciaSqlDAO.updateProvincia(id, nombre, id_departamento);
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
            rpta = provinciaSqlDAO.deleteProvincia(id);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }
        return rpta;
    };
}
