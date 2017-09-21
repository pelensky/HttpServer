package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

import java.io.IOException;

public class Coffee extends Route {

    @Override
    public boolean respondsTo(Request request) {
        return request.getUri().equals("coffee");
    }

    @Override
    public Response get(Request request) throws IOException {
        return new Response(Status.TEAPOT.code(), null, Status.TEAPOT.message().getBytes());
    }
}
