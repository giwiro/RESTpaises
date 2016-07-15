package app.controllers;


import app.Application;
import app.DAO.mysql.PaisSqlDAO;
import app.models.mysql.Pais;
import com.google.gson.Gson;
import spark.Route;

import java.util.List;

public class PaisController {

    public static Route handleReadPaises = (request, response) -> {
        PaisSqlDAO paisDAO = new PaisSqlDAO(Application.mysqlConnection);
        List<Pais> paises = paisDAO.getAllPaises();
        String json = new Gson().toJson(paises);
        return json;

    };
}
