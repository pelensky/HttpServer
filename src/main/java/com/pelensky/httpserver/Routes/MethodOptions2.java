package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Response;

public class MethodOptions2 implements Route {

    public String route() {
        return "/method_options2";
    }

    public Response optionsCode() {
        return new Response(200);
    }
}
