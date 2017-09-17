package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

public class Default extends Route {
    @Override
    public String route() {
        return "";
    }

    @Override
    public Response get(Request request) {
        return new Response(200);
    }

    @Override
    public Response head(Request request) {
        return new Response(200);
    }
}
