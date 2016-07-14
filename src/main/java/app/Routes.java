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


        // READ Pais
        @Getter
        public static final String READ_PAISES = "/paises/read";

        // CRUD Depa
        @Getter
        public static final String CREATE_DEPARTAMENTO = "/departamentos/create";
        @Getter
        public static final String READ_DEPARTAMENTOS = "/departamentos/read";
        @Getter
        public static final String UPDATE_DEPARTAMENTO = "/departamentos/update";
        @Getter
        public static final String DELETE_DEPARTAMENTO = "/departamentos/delete";

        // CRUD Prov
        @Getter
        public static final String CREATE_PROVINCIA = "/provincias/create";
        @Getter
        public static final String READ_PROVINCIAS = "/provincias/read";
        @Getter
        public static final String UPDATE_PROVINCIA = "/provincias/update";
        @Getter
        public static final String DELETE_PROVINCIA = "/provincias/delete";

        // CRUD Dist
        @Getter
        public static final String CREATE_DISTRITO = "/distritos/create";
        @Getter
        public static final String READ_DISTRITOS = "/distritos/read";
        @Getter
        public static final String UPDATE_DISTRITO = "/distritos/update";
        @Getter
        public static final String DELETE_DISTRITO = "/distritos/delete";
    }

    public static class Template {
        public final static String INDEX = "/templates/index.vm";
        public final static String CARGA_MASIVA = "/templates/carga_masiva.vm";
    }
}
