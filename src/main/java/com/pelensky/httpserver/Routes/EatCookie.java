package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

public class EatCookie extends Route {
    @Override
    public String route() {
        return "eat_cookie";
    }

    @Override
    public Response get(Request request) {
        StringBuilder body = new StringBuilder();
        body.append("mmmm ");
        if (request.hasCookies()) {
            body.append(request.getCookie().get("type"));
        }
        return new Response(Status.OK.code(), null, String.valueOf(body).getBytes());
    }
}
