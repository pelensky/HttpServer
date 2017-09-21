package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

public class NotFound extends Route {
    @Override
    public boolean respondsTo(Request request) {
        return true;
    }

    @Override
    public Response get(Request request) {
        return new Response(Status.NOT_FOUND.code());
    }

    @Override
    public Response head (Request request) {
        return new Response(Status.NOT_FOUND.code());
    }

    @Override
    public Response post (Request request) {
        return new Response(Status.NOT_FOUND.code());
    }

    @Override
    public Response put (Request request) {
        return new Response(Status.NOT_FOUND.code());
    }

    @Override
    public Response delete (Request request) {
        return new Response(Status.NOT_FOUND.code());
    }

    @Override
    public Response patch (Request request) {
        return new Response(Status.NOT_FOUND.code());
    }

}
