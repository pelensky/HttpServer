package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

public class Form implements Route {
    private String body;

    @Override
    public String route() {
        return "form";
    }

    @Override
    public Response get(Request request) {
        return (body != null) ? new Response(200, null, body) : new Response(200);
    }

    @Override
    public Response post(Request request) {
        body = request.getBody();
        return new Response(200, null, body);
    }

    @Override
    public Response put(Request request) {
        body = request.getBody();
        return new Response(200);
    }

    @Override
    public Response delete(Request request) {
        body = null;
        return new Response(200);
    }
}
