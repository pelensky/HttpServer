package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public interface Route {

    String route();

    default Response call(Request request) throws IOException {
        String method = request.getMethod();
        switch (method) {
            case "GET":
                return get(request);
            case "HEAD":
                return this.head(request);
            case "POST":
                return this.post(request);
            case "OPTIONS":
                return this.options(request);
            case "PUT":
                return this.put(request);
            case "DELETE":
                return this.delete(request);
            default:
                return new Response(405);
        }
    }

    default Response get(Request request) throws IOException {
        return new Response(405);
    }

    default Response head(Request request) {
        return new Response(405);
    }

    default Response post(Request request) {
        return new Response(405);
    }

    default Response options(Request request) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Allow", getOptions(request));
        Response response = new Response(optionsCode(request).getStatusCode(), headers);
        return (optionsCode(request).getStatusCode() != 405) ? response : optionsCode(request);
    }

    default Response optionsCode(Request request) {
        return new Response(405);
    }

    default Response put(Request request) {
        return new Response(405);
    }

    default Response delete(Request request) {
        return new Response(405);
    }

    default String getOptions(Request request) throws IOException {
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
