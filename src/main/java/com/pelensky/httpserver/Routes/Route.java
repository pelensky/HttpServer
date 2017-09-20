package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.StatusCodes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Route {

    public abstract String route();

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
                return new Response(StatusCodes.METHOD_NOT_ALLOWED);
        }
    }

    public Response get(Request request) throws IOException {
        return new Response(StatusCodes.METHOD_NOT_ALLOWED);
    }

    public Response head(Request request) {
        return new Response(StatusCodes.METHOD_NOT_ALLOWED);
    }

    public Response post(Request request){
        return new Response(StatusCodes.METHOD_NOT_ALLOWED);
    }

    private Response options(Request request) throws IOException, NoSuchAlgorithmException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Allow", getOptions(request));
        Response response = new Response(optionsCode(request).getStatusCode(), headers);
        return (!optionsCode(request).getStatusCode().equals(StatusCodes.METHOD_NOT_ALLOWED)) ? response : optionsCode(request);
    }

    public Response optionsCode(Request request) {
        return new Response(StatusCodes.METHOD_NOT_ALLOWED);
    }

    public Response put(Request request) {
        return new Response(StatusCodes.METHOD_NOT_ALLOWED);
    }

    public Response delete(Request request) {
        return new Response(StatusCodes.METHOD_NOT_ALLOWED);
    }

    public Response patch(Request request) throws IOException, NoSuchAlgorithmException {
        return new Response(StatusCodes.METHOD_NOT_ALLOWED);
    }

    String getOptions(Request request) throws IOException, NoSuchAlgorithmException {
        List<String> options = new ArrayList<>();
        options.add((!get(request).getStatusCode().equals(StatusCodes.METHOD_NOT_ALLOWED) ) ? "GET" : null);
        options.add((!head(request).getStatusCode().equals(StatusCodes.METHOD_NOT_ALLOWED) ) ? "HEAD" : null);
        options.add((!post(request).getStatusCode().equals(StatusCodes.METHOD_NOT_ALLOWED) ) ? "POST" : null);
        options.add((!optionsCode(request).getStatusCode().equals(StatusCodes.METHOD_NOT_ALLOWED) ) ? "OPTIONS" : null);
        options.add((!put(request).getStatusCode().equals( StatusCodes.METHOD_NOT_ALLOWED)) ? "PUT" : null);
        options.add((!delete(request).getStatusCode().equals( StatusCodes.METHOD_NOT_ALLOWED)) ? "DELETE" : null);
        options.add((!patch(request).getStatusCode().equals( StatusCodes.METHOD_NOT_ALLOWED)) ? "PATCH" : null);
        return options.stream().filter(Objects::nonNull).collect(Collectors.joining(","));
    }

}
