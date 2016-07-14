package app;

import app.Routes;
import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

import app.controllers.CargaMasivaController;
import app.controllers.IndexController;
import spark.Route;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Vicky on 12/07/2016.
 */
public class Application {

    private final static int PORT = 8080;

    // Publico para poder acceder a el desde los controladores, y tener una sola instancia.
    // Podria ser un singleton para darle robustez  (?)

    public static Connection mysqlConnection;

    public static File tmpDir;

    public static void main(String[] args) {

        // Probando existencia de librerias
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No hay driver de MySQL");
            e.printStackTrace();
            stop();
            return;
        }

        System.out.println("Driver de MySQL cargado");
        try {
            String user = "giwiro";
            String pass = "prograinter123";
            mysqlConnection = DriverManager
                    .getConnection("jdbc:mysql://localhost/db_test?user=" + user + "&password=" + pass);
        } catch (SQLException e) {
            e.printStackTrace();
            stop();
            return;
        }

        System.out.println("Mysql Coneccion: Establecida");

        // Crear carpeta temporal para subir archivos
        tmpDir = new File("/temp");
        tmpDir.mkdir();

        port(PORT);
        enableDebugScreen();

        get(Routes.Web.INDEX,                   IndexController.serveIndexPage);
        get(Routes.Web.CARGA_MASIVA_PAGE,       CargaMasivaController.serveCargaMasivaPage);
        post(Routes.Web.CARGA_MASIVA_UPLOAD,    CargaMasivaController.handleUploadFile);

    }

}
