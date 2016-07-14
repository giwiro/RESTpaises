package app.controllers;

import app.Application;
import app.DAO.mysql.DepartamentoDAO;
import app.models.Departamento;
import com.google.gson.Gson;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import spark.Route;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class DepartamentoController {

    static DepartamentoDAO departamentoDAO = new DepartamentoDAO(Application.mysqlConnection);

    public static Route handleReadDepartamentos = (request, response) -> {
        List<Departamento> departamentoList = departamentoDAO.getAllDepartamentos();
        String json = new Gson().toJson(departamentoList);
        return json;
    };

    public static Route handleCreateDepartamento = (request, response) -> {
        boolean rpta = false;
        String nombre = request.queryParams("nombre");
        int id_pais;
        try{
            id_pais = Integer.parseInt(request.queryParams("id_pais"));
            rpta = departamentoDAO.createDepartamento(nombre, id_pais);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }

        return rpta;
    };

    public static Route handleUpdateDepartamento = (request, response) -> {
        boolean rpta = false;
        String nombre = request.queryParams("nombre");
        int id;
        int id_pais;

        try{
            id_pais = Integer.parseInt(request.queryParams("id_pais"));
            id = Integer.parseInt(request.queryParams("id"));
            rpta = departamentoDAO.updateDepartamento(id, nombre, id_pais);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }
        return rpta;
    };

    public static Route handleDeleteDepartamento = (request, response) -> {
        boolean rpta = false;
        int id;

        try{
            id = Integer.parseInt(request.queryParams("id"));
            rpta = departamentoDAO.deleteDepartamento(id);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }
        return rpta;
    };
}
