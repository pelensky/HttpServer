package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

import java.util.HashMap;
import java.util.Map;

public class Cookie extends Route {
    @Override
    public String route() {
        return "cookie";
    }

    @Override
    public Response get(Request request) {
        Map<String, String> headers = new HashMap<>();
        if (request.hasParameters()) {
            StringBuilder cookies = new StringBuilder();
            request.getParameters().forEach((key, value) -> cookies.append(key).append("=").append(value).append(System.lineSeparator()));
            headers.put("Set-Cookie", String.valueOf(cookies));
        }
        return new Response(Status.OK.code(), headers, "Eat".getBytes());
    }
}
