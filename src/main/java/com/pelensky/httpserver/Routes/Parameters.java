package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

public class Parameters extends Route {

    @Override
    public boolean respondsTo(Request request) {
        return request.getUri().equals("parameters");
    }

    @Override
    public Response get(Request request) {
        StringBuilder body = new StringBuilder();
        if (request.hasParameters()) {
            request.getParameters().forEach((key, value) -> body.append(key).append(" = ").append(value).append(System.lineSeparator()));
        }
        return new Response(Status.OK.code(), null, String.valueOf(body).trim().getBytes());
    }
}
