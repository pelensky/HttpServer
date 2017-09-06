package Validations;

import java.util.ArrayList;
import java.util.List;

public class Routes {
    private static List<String> list() {
        List<String> routesList = new ArrayList<>();
        routesList.add("/");
        routesList.add("/form");
        routesList.add("/method_options");
        routesList.add("/method_options2");
        return routesList;
    }

    public static boolean containsValidRoute(String input) {
        String route = getRoute(input);
        return list().contains(route);
    }

    public static String getRoute(String input) {
        String[] words = input.trim().split(" ");
        return words[1];
    }
}
