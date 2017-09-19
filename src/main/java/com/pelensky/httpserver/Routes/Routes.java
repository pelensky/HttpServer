package com.pelensky.httpserver.Routes;

import java.util.ArrayList;
import java.util.List;

public class Routes {
    private static final Route defaultRoute = new Default();
    private static final Route form = new Form();
    private static final Route methodOptions = new MethodOptions();
    private static final Route methodOptions2 = new MethodOptions2();
    private static final Route redirect = new Redirect();
    private static final Route coffee = new Coffee();
    private static final Route tea = new Tea();
    private static final Route logs = new Logs();
    private static final Route parameters = new Parameters();
    private static final Route cookie = new Cookie();
    private static final Route eatCookie = new EatCookie();

    public static List<Route> routes() {
        List<Route> routesList = new ArrayList<>();
        routesList.add(defaultRoute);
        routesList.add(form);
        routesList.add(methodOptions);
        routesList.add(methodOptions2);
        routesList.add(redirect);
        routesList.add(coffee);
        routesList.add(tea);
        routesList.add(logs);
        routesList.add(parameters);
        routesList.add(cookie);
        routesList.add(eatCookie);
        return routesList;
    }
}
