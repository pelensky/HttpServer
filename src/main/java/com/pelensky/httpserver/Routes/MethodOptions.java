package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

public class MethodOptions implements Route {

    @Override
    public String route() {
        return "method_options";
    }

    @Override
    public Response head(Request request) {
        return new Response(200);
    }

    @Override
    public Response post(Request request) {
        return new Response(200);
    }

    @Override
    public Response optionsCode(Request request) {
        return new Response(200);
    }

    @Override
    public Response put(Request request) {
        return new Response(200);
    }
}
