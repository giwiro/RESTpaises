package app;

import lombok.Getter;

/**
 * Created by Vicky on 12/07/2016.
 */
public class Routes {

    public static class Web {
        @Getter
        public static final String INDEX = "/";
    }

    public static class Template {
        public final static String INDEX = "/templates/index.vm";
    }
}
