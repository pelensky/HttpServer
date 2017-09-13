package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

public class MethodOptions2 implements Route {

    @Override
    public String route() {
        return "method_options2";
    }

    @Override
    public Response optionsCode(Request request) {
        return new Response(200);
    }
}
