package Validations;

import java.util.HashMap;
import java.util.Map;

public class Options {

    private static Map<String, String> list() {
        Map<String, String> options = new HashMap<>();
        options.put("/method_options", "GET,HEAD,POST,OPTIONS,PUT");
        options.put("/method_options2", "GET,OPTIONS");
        return options;
    }

    public static String getOptions(String route) {
        return list().getOrDefault(route, "GET,HEAD,POST,OPTIONS,PUT");
    }
}
