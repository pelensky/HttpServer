package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;
import com.pelensky.httpserver.Utilities.LoggingTool;

import java.io.IOException;

public class Logs extends Route {

    @Override
    public boolean respondsTo(Request request) {
        return request.getUri().equals("logs");
    }

    @Override
    public Response get(Request request) throws IOException {
        return new Response(Status.OK.code(), null, LoggingTool.showLogs());
    }
}
