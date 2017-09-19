package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

public class EatCookie extends Route {
    @Override
    public String route() {
        return "eat_cookie";
    }

    @Override
    public Response get(Request request) {
        StringBuilder body = new StringBuilder();
        body.append("mmmm ");
        if (request.getHeaders() != null) {
        String cookies = request.getHeaders().get("Cookie");
        body.append(cookies.split("=")[1]);
        }
        return new Response(200, null, String.valueOf(body).getBytes());
    }
}
