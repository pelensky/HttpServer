package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

public class Tea implements Route {
    @Override
    public String route() {
        return "tea";
    }

    @Override
    public Response get(Request request) {
        return new Response(200);
    }
}
