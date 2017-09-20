package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.StatusCodes;

public class MethodOptions extends Route {

    @Override
    public String route() {
        return "method_options";
    }


    @Override
    public Response get(Request request) {
        return new Response(StatusCodes.OK);
    }

    @Override
    public Response head(Request request) {
        return new Response(StatusCodes.OK);
    }

    @Override
    public Response post(Request request) {
        return new Response(StatusCodes.OK);
    }

    @Override
    public Response optionsCode(Request request) {
        return new Response(StatusCodes.OK);
    }

    @Override
    public Response put(Request request) {
        return new Response(StatusCodes.OK);
    }
}
