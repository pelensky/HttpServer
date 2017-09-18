package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Authentication;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.util.HashMap;
import java.util.Map;

public class Logs extends Route {
    private final String username = "admin";
    private final String password = "hunter2";

    @Override
    public String route() {
        return "logs";
    }

    @Override
    public Response get(Request request) {
        if (new Authentication(username, password).isAuthenticated(request)) {
            return new Response(200);
        } else {
            Map<String, String> header = new HashMap<>();
            header.put("WWW-Authenticate", "Basic realm=\"Authentication required\"");
            return new Response(401, header);
        }
    }
}
