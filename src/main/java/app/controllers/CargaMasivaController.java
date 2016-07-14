package app.controllers;

import app.Application;
import app.Routes;
import app.utils.ScriptRunner;
import app.utils.ViewUtil;
import spark.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gi Wah Davalos on 13/07/2016.
 */
public class CargaMasivaController {

    public static Route serveCargaMasivaPage = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, Routes.Template.CARGA_MASIVA);
    };

    public static Route handleUploadFile = (request, response) -> {
        Path tempFile = Files.createTempFile(Application.tmpDir.toPath(), "", "");
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        Part filePart = request.raw().getPart("file");
        File file;
        try (InputStream input = filePart.getInputStream()) { // getPart needs to use same "name" as input field in form
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            file = new File(tempFile.toString());
            System.out.println("Uploaded: " + file.getAbsolutePath());

            // Ejecutar la carga masiva con el archivo
            ScriptRunner runner = new ScriptRunner(Application.mysqlConnection, false, false);
            runner.runScript(new BufferedReader(new FileReader(file)));
            System.out.println("Se corrió el archivo");
            file.delete();
            System.out.println("Procediendo a borrar el archivo");
        }
        return "Se realizo la ejecucion del archivo";
    };
}
