package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

public class Tea extends Route {
    @Override
    public String route() {
        return "tea";
    }

    @Override
    public Response get(Request request) {
        return new Response(Status.OK.code());
    }
}
