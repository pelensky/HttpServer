package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;
import com.pelensky.httpserver.Utilities.HtmlFormatter;

import java.io.IOException;

public class Default extends Route {

    @Override
    public boolean respondsTo(Request request) {
        return request.getUri().equals("");
    }

    @Override
    public Response get(Request request) throws IOException {
        return new Response(Status.OK.code(), null, new HtmlFormatter().format("HttpServer", new FileProcessor().getPublicFolderContents()).getBytes());
    }

    @Override
    public Response head(Request request) {
        return new Response(Status.OK.code());
    }
}
