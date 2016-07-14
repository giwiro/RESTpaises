package app;

import lombok.Getter;

/**
 * Created by Vicky on 12/07/2016.
 */
public class Routes {

    public static class Web {
        @Getter
        public static final String INDEX = "/";

        @Getter
        public static final String CARGA_MASIVA_PAGE = "/cargaMasiva";
        @Getter
        public static final String CARGA_MASIVA_UPLOAD = "/cargaMasiva/upload";
    }

    public static class Template {
        public final static String INDEX = "/templates/index.vm";
        public final static String CARGA_MASIVA = "/templates/carga_masiva.vm";
    }
}
