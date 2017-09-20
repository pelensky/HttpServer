package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.StatusCodes;

import java.io.IOException;

public class Default extends Route {
    @Override
    public String route() {
        return "";
    }

    @Override
    public Response get(Request request) throws IOException {
        byte[] body = new FileProcessor().displayDirectoryContentsAsLinks();
        return new Response(StatusCodes.OK, null, body);
    }

    @Override
    public Response head(Request request) {
        return new Response(StatusCodes.OK);
    }
}
