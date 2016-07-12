package app.controllers;

import app.Routes;
import app.utils.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vicky on 12/07/2016.
 */
public class IndexController {
    public static Route serveIndexPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, Routes.Template.INDEX);
    };
}
