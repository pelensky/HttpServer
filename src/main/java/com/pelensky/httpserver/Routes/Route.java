package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Route {

    public abstract boolean respondsTo(Request request);

    public Response call(Request request) throws IOException, NoSuchAlgorithmException {
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
            case "PATCH":
                return patch(request);
            default:
                return new Response(Status.METHOD_NOT_ALLOWED.code());
        }
    }

    public Response get(Request request) throws IOException {
        return new Response(Status.METHOD_NOT_ALLOWED.code());
    }

    public Response head(Request request) {
        return new Response(Status.METHOD_NOT_ALLOWED.code());
    }

    public Response post(Request request){
        return new Response(Status.METHOD_NOT_ALLOWED.code());
    }

    private Response options(Request request) throws IOException, NoSuchAlgorithmException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Allow", getOptions(request));
        Response response = new Response(optionsCode(request).getStatusCode(), headers);
        return (!optionsCode(request).getStatusCode().equals(Status.METHOD_NOT_ALLOWED.code())) ? response : optionsCode(request);
    }

    public Response optionsCode(Request request) {
        return new Response(Status.METHOD_NOT_ALLOWED.code());
    }

    public Response put(Request request) {
        return new Response(Status.METHOD_NOT_ALLOWED.code());
    }

    public Response delete(Request request) {
        return new Response(Status.METHOD_NOT_ALLOWED.code());
    }

    public Response patch(Request request) throws IOException, NoSuchAlgorithmException {
        return new Response(Status.METHOD_NOT_ALLOWED.code());
    }

    String getOptions(Request request) throws IOException, NoSuchAlgorithmException {
        List<String> options = new ArrayList<>();
        options.add((!get(request).getStatusCode().equals(Status.METHOD_NOT_ALLOWED.code()) ) ? "GET" : null);
        options.add((!head(request).getStatusCode().equals(Status.METHOD_NOT_ALLOWED.code()) ) ? "HEAD" : null);
        options.add((!post(request).getStatusCode().equals(Status.METHOD_NOT_ALLOWED.code()) ) ? "POST" : null);
        options.add((!optionsCode(request).getStatusCode().equals(Status.METHOD_NOT_ALLOWED.code()) ) ? "OPTIONS" : null);
        options.add((!put(request).getStatusCode().equals( Status.METHOD_NOT_ALLOWED.code())) ? "PUT" : null);
        options.add((!delete(request).getStatusCode().equals( Status.METHOD_NOT_ALLOWED.code())) ? "DELETE" : null);
        options.add((!patch(request).getStatusCode().equals( Status.METHOD_NOT_ALLOWED.code())) ? "PATCH" : null);
        return options.stream().filter(Objects::nonNull).collect(Collectors.joining(","));
    }

}
