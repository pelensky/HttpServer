package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

public class Form extends Route {
private byte[] body;

    @Override
    public String route() {
        return "form";
    }

    @Override
    public Response get(Request request) {
        return (body != null) ? new Response(Status.OK.code(), null, body) : new Response(Status.OK.code());
    }

    @Override
    public Response post(Request request) {
        setBody(request);
        return new Response(Status.OK.code(), null, body);
    }

    @Override
    public Response put(Request request) {
        setBody(request);
        return new Response(Status.OK.code());
    }

    @Override
    public Response delete(Request request) {
        body = null;
        return new Response(Status.OK.code());
    }

    private void setBody(Request request) {
        if (request.hasBody()) {
            body = request.getBody().getBytes();
        }
    }
}
