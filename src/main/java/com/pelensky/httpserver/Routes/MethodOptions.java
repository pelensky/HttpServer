package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Response;

public class MethodOptions implements Route {

    public String route() {
        return "/method_options";
    }

    public Response head() {
        return new Response(200);
    }

    public Response post() {
        return new Response(200);
    }

    public Response optionsCode() {
        return new Response(200);
    }

    public Response put() {
        return new Response(200);
    }
}
