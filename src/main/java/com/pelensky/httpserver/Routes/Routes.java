package com.pelensky.httpserver.Routes;

import java.util.ArrayList;
import java.util.List;

public class Routes {
    public static List<Route> routes() {
        List<Route> routesList = new ArrayList<>();
        routesList.add(new Default());
        routesList.add(new Form());
        routesList.add(new MethodOptions());
        routesList.add(new MethodOptions2());
        routesList.add(new Redirect());
        return routesList;
    }
}
