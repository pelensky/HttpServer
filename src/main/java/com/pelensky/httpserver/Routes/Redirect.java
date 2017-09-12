package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.util.HashMap;
import java.util.Map;

public class Redirect implements Route {

    @Override
    public String route() {
        return "/redirect";
    }

    @Override
    public Response get(Request request) {
        Map<String, String> responseHeaders = new HashMap<>();
        responseHeaders.put("Location", "/");
        return new Response(302, responseHeaders);
    }
}
