package com.pelensky.httpserver;

import java.util.ArrayList;
import java.util.List;

class Routes {
    private static List<String> list() {
        List<String> routesList = new ArrayList<>();
        routesList.add("/ ");
        routesList.add("/form ");
        return routesList;
    }

    static boolean containsValidRoute(String input) {
        for (String route : list()) {
            if (input.contains(route)) {
                return true;
            }
        }
        return false;
    }
}
