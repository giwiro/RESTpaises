package app;

import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

import app.controllers.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import spark.Route;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Application {

    private final static int PORT = 8080;

    // Publico para poder acceder a el desde los controladores, y tener una sola instancia.
    // Podria ser un singleton para darle robustez  (?)

    public static Connection mysqlConnection;
    public static MongoDatabase mongoDB;

    public static File tmpDir;

    private static void config() {
        // Probando existencia de librerias
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No hay driver de MySQL");
            e.printStackTrace();
            stop();
            return;
        }
        System.out.println("Drivers de MySQL y Mongo cargado");

        try {
            String user = "giwiro";
            String pass = "prograinter123";
            mysqlConnection = DriverManager
                    .getConnection("jdbc:mysql://localhost/db_test?user=" + user + "&password=" + pass);
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            mongoDB = mongoClient.getDatabase("test_db");
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

    }

    public static void main(String[] args) {

        config();

        get(Routes.Web.INDEX,                   IndexController.serveIndexPage);
        get(Routes.Web.CARGA_MASIVA_PAGE,       CargaMasivaController.serveCargaMasivaPage);
        post(Routes.Web.CARGA_MASIVA_UPLOAD,    CargaMasivaController.handleUploadFile);

        // Crear documentos
        post(Routes.Web.CREAR_DOCUMENTOS,       GenerarDocumentosController.handleGenerateDoc);


        //CRUD
        post(Routes.Web.READ_PAISES,            PaisController.handleReadPaises);


        post(Routes.Web.CREATE_DEPARTAMENTO,    DepartamentoController.handleCreateDepartamento);
        post(Routes.Web.READ_DEPARTAMENTOS,     DepartamentoController.handleReadDepartamentos);
        post(Routes.Web.UPDATE_DEPARTAMENTO,    DepartamentoController.handleUpdateDepartamento);
        post(Routes.Web.DELETE_DEPARTAMENTO,    DepartamentoController.handleDeleteDepartamento);

        post(Routes.Web.CREATE_PROVINCIA,       ProvinciaController.handleCreateProvincia);
        post(Routes.Web.READ_PROVINCIAS,        ProvinciaController.handleReadProvincias);
        post(Routes.Web.UPDATE_PROVINCIA,       ProvinciaController.handleUpdateProvincia);
        post(Routes.Web.DELETE_PROVINCIA,       ProvinciaController.handleDeleteProvincia);

        post(Routes.Web.CREATE_DISTRITO,        DistritoController.handleCreateDistrito);
        post(Routes.Web.READ_DISTRITOS,         DistritoController.handleReadDistritos);
        post(Routes.Web.UPDATE_DISTRITO,        DistritoController.handleUpdateDistrito);
        post(Routes.Web.DELETE_DISTRITO,        DistritoController.handleDeleteDistrito);


    }

}
