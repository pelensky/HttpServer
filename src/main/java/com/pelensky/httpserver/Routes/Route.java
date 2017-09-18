package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Route {

    public abstract String route();

    public Response call(Request request) throws IOException {
        String method = request.getMethod();

        switch (method) {
            case "GET":
                return get(request);
            case "HEAD":
                return head(request);
            case "POST":
                return post(request);
            case "OPTIONS":
                return options(request);
            case "PUT":
                return put(request);
            case "DELETE":
                return delete(request);
            default:
                return new Response(405);
        }
    }

    public Response get(Request request) throws IOException {
        return new Response(405);
    }

    public Response head(Request request) {
        return new Response(405);
    }

    public Response post(Request request) {
        return new Response(405);
    }

    private Response options(Request request) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Allow", getOptions(request));
        Response response = new Response(optionsCode(request).getStatusCode(), headers);
        return (optionsCode(request).getStatusCode() != 405) ? response : optionsCode(request);
    }

    public Response optionsCode(Request request) {
        return new Response(405);
    }

    public Response put(Request request) {
        return new Response(405);
    }

    public Response delete(Request request) {
        return new Response(405);
    }

    String getOptions(Request request) throws IOException {
        List<String> options = new ArrayList<>();
        options.add((get(request).getStatusCode() != 405 ) ? "GET" : null);
        options.add((head(request).getStatusCode() != 405 ) ? "HEAD" : null);
        options.add((post(request).getStatusCode() != 405 ) ? "POST" : null);
        options.add((optionsCode(request).getStatusCode() != 405 ) ? "OPTIONS" : null);
        options.add((put(request).getStatusCode() != 405 ) ? "PUT" : null);
        options.add((delete(request).getStatusCode() != 405) ? "DELETE" : null);
        return options.stream().filter(Objects::nonNull).collect(Collectors.joining(","));
    }

}
