package com.pelensky.httpserver.Routes;

import java.util.ArrayList;
import java.util.List;

public class Routes {
    private static Route defaultRoute = new Default();
    private static Route form = new Form();
    private static Route methodOptions = new MethodOptions();
    private static Route methodOptions2 = new MethodOptions2();
    private static Route redirect = new Redirect();

    public static List<Route> routes() {
        List<Route> routesList = new ArrayList<>();
        routesList.add(defaultRoute);
        routesList.add(form);
        routesList.add(methodOptions);
        routesList.add(methodOptions2);
        routesList.add(redirect);
        return routesList;
    }
}
