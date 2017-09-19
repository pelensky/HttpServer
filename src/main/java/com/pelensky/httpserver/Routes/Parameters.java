package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

public class Parameters extends Route {
    @Override
    public String route() {
        return "parameters";
    }

    @Override
    public Response get(Request request) {
        StringBuilder body = new StringBuilder();
        if (request.getParameters() != null) {
            request.getParameters().forEach((key, value) -> body.append(key).append(" = ").append(value).append(System.lineSeparator()));
        }
        return new Response(200, null, String.valueOf(body).trim().getBytes());
    }
}
