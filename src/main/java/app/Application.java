package app;

import app.Routes;
import static spark.Spark.*;
import static spark.debug.DebugScreen.*;
import app.controllers.IndexController;

/**
 * Created by Vicky on 12/07/2016.
 */
public class Application {

    private final static int PORT = 8080;

    public static void main(String[] args) {

        port(PORT);
        enableDebugScreen();

        get(Routes.Web.INDEX, IndexController.serveIndexPage);

    }

}
