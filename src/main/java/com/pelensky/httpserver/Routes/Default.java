package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Response;

public class Default implements Route {
    public String route() {
        return "/";
    }

    public Response head() {
        return new Response(200);
    }
}
