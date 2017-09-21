package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

public class MethodOptions2 extends Route {

    @Override
    public boolean respondsTo(Request request) {
        return request.getUri().equals("method_options2");
    }

    @Override
    public Response get(Request request) {
        return new Response(Status.OK.code());
    }

    @Override
    public Response optionsCode(Request request) {
        return new Response(Status.OK.code());
    }
}
