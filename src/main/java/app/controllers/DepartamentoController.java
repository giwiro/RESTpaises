package app.controllers;

import app.Application;
import app.DAO.mysql.DepartamentoSqlDAO;
import app.models.mysql.Departamento;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.SQLException;
import java.util.List;


public class DepartamentoController {

    static DepartamentoSqlDAO departamentoSqlDAO = new DepartamentoSqlDAO(Application.mysqlConnection);

    public static Route handleReadDepartamentos = (request, response) -> {
        List<Departamento> departamentoList = departamentoSqlDAO.getAllDepartamentos();
        String json = new Gson().toJson(departamentoList);
        return json;
    };

    public static Route asdasds = new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            return null;
        }
    };

    public static Route handleCreateDepartamento = (request, response) -> {
        boolean rpta = false;
        String nombre = request.queryParams("nombre");
        int id_pais;
        try{
            id_pais = Integer.parseInt(request.queryParams("id_pais"));
            rpta = departamentoSqlDAO.createDepartamento(nombre, id_pais);
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
            rpta = departamentoSqlDAO.updateDepartamento(id, nombre, id_pais);
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
            rpta = departamentoSqlDAO.deleteDepartamento(id);
        }catch(NumberFormatException | SQLException e){
            rpta = false;
        }
        return rpta;
    };
}
