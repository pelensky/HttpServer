package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.StatusCodes;

import java.io.IOException;

public class Coffee extends Route {
    @Override
    public String route() {
        return "coffee";
    }

    @Override
    public Response get(Request request) throws IOException {
        return new Response(StatusCodes.TEAPOT, null, "I'm a teapot".getBytes());
    }
}
