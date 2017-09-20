package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.StatusCodes;

public class Form extends Route {
private byte[] body;

    @Override
    public String route() {
        return "form";
    }

    @Override
    public Response get(Request request) {
        return (body != null) ? new Response(StatusCodes.OK, null, body) : new Response(StatusCodes.OK);
    }

    @Override
    public Response post(Request request) {
        setBody(request);
        return new Response(StatusCodes.OK, null, body);
    }

    @Override
    public Response put(Request request) {
        setBody(request);
        return new Response(StatusCodes.OK);
    }

    @Override
    public Response delete(Request request) {
        body = null;
        return new Response(StatusCodes.OK);
    }

    private void setBody(Request request) {
        if (request.hasBody()) {
            body = request.getBody().getBytes();
        }
    }
}
