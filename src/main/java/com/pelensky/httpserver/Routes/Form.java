package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Response;

public class Form implements Route{

    public String route() {
        return "/form";
    }

    public Response post() {
        return new Response(200);
    }

    public Response put() {
        return new Response(200);
    }
}
