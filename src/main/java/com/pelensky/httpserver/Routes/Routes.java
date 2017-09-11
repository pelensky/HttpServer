package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Routes.*;

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

    public static List<Route> routes() {
        List<Route> routesList = new ArrayList<>();
        routesList.add(new Default());
        routesList.add(new Form());
        routesList.add(new MethodOptions());
        routesList.add(new MethodOptions2());
        return routesList;
    }

    public static boolean containsValidRoute(String route) {
        return list().contains(route);
    }
}
