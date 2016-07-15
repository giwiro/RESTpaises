package app.utils;

import app.Routes;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

public class ViewUtil {

    private static VelocityTemplateEngine engine;

    public static String render(Request request, Map<String, Object> model, String templatePath) {

        model.put("WebPath", Routes.Web.class);

        if (model.get("titulo") == null) {
            model.put("titulo", "REST paises");
        }
        return provideRenderer().render(new ModelAndView(model, templatePath));
    }

    private static VelocityTemplateEngine provideRenderer() {
        if (engine == null) {
            engine = new VelocityTemplateEngine();
        }
        return engine;
    }
}
