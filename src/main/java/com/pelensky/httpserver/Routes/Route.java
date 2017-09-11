package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Response;

import java.util.*;
import java.util.stream.Collectors;

public interface Route {
    String route();

    default Response call(String method) {
        switch (method) {
            case "GET":
                return this.get();
            case "HEAD":
                return this.head();
            case "POST":
                return this.post();
            case "OPTIONS":
                return this.options();
            case "PUT":
                return this.put();
            default:
                return new Response(404);
        }
    }

    default Response get() {
        return new Response(200);
    };

    default Response head() {
        return new Response(405);
    }

    default Response post() {
        return new Response(405);
    }

    default Response options() {
        boolean isOptionsAllowed = optionsCode().getStatusCode() != 405;
        Map<String, String> headers = new HashMap<>();
        headers.put("Allow", getOptions());
        Response response = new Response(optionsCode().getStatusCode(), headers);
        return (isOptionsAllowed) ? response : optionsCode();
    }

    default Response optionsCode() {
        return new Response(405);
    }

    default Response put() {
        return new Response(405);
    }

    default String getOptions() {
        List<String> options = new ArrayList<>();
        options.add((get().getStatusCode() != 405 ) ? "GET" : null);
        options.add((head().getStatusCode() != 405 ) ? "HEAD" : null);
        options.add((post().getStatusCode() != 405 ) ? "POST" : null);
        options.add((optionsCode().getStatusCode() != 405 ) ? "OPTIONS" : null);
        options.add((put().getStatusCode() != 405 ) ? "PUT" : null);
        return options.stream().filter(Objects::nonNull).collect(Collectors.joining(","));
    }

}
