package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

public class EatCookie extends Route {

    @Override
    public boolean respondsTo(Request request) {
        return request.getUri().equals("eat_cookie");
    }

    @Override
    public Response get(Request request) {
        StringBuilder body = new StringBuilder();
        body.append("mmmm ");
        if (request.hasCookies()) {
            body.append(request.findCookies().get("type"));
        }
        return new Response(Status.OK.code(), null, String.valueOf(body).getBytes());
    }
}
