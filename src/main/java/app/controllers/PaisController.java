package app.controllers;


import app.Application;
import app.DAO.mysql.PaisDAO;
import app.models.Pais;
import com.google.gson.Gson;
import spark.Route;

import java.util.List;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class PaisController {

    public static Route handleReadPaises = (request, response) -> {
        PaisDAO paisDAO = new PaisDAO(Application.mysqlConnection);
        List<Pais> paises = paisDAO.getAllPaises();
        String json = new Gson().toJson(paises);
        return json;

    };
}
