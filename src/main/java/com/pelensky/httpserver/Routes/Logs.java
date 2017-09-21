package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.ResponseHeader;
import com.pelensky.httpserver.Utilities.LoggingTool;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;
import com.pelensky.httpserver.Utilities.Authentication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Logs extends Route {

    @Override
    public boolean respondsTo(Request request) {
        return request.getUri().equals("logs");
    }

    @Override
    public Response get(Request request) throws IOException {
        final String username = "admin";
        final String password = "hunter2";
        if (new Authentication(username, password).isAuthenticated(request)) {
            return new Response(Status.OK.code(), null, LoggingTool.showLogs());
        } else {
            Map<String, String> header = new HashMap<>();
            header.put(ResponseHeader.WWW_AUTHENTICATE.header(), "Basic realm=\"Authentication required\"");
            return new Response(Status.UNAUTHORIZED.code(), header);
        }
    }
}
