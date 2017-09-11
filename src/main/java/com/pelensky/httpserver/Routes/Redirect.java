package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Response;

import java.util.HashMap;
import java.util.Map;

public class Redirect implements Route {

    public String route() {
        return "/redirect";
    }

    public Response get() {
        Map<String, String> responseHeaders = new HashMap<>();
        responseHeaders.put("Location", "/");
        return new Response(302, responseHeaders);
    }
}
