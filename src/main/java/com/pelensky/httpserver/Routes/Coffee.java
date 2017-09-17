package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;

public class Coffee implements Route {
    @Override
    public String route() {
        return "coffee";
    }

    @Override
    public Response get(Request request) throws IOException {
        return new Response(418, null, "I'm a teapot".getBytes());
    }
}
