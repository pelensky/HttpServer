package com.pelensky.httpserver.Router;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.ResponseHeader;
import com.pelensky.httpserver.Response.Status;
import com.pelensky.httpserver.Routes.Route;
import com.pelensky.httpserver.Utilities.BasicAuthorization;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Router {

    public static Response findResponse(BasicAuthorization basicAuthorization, Request request) throws IOException, NoSuchAlgorithmException {
        if (basicAuthorization.isAuthenticationRequired(request)){
            if (basicAuthorization.isAuthenticated(request)) {
                return getResponse(request);
            }
            return requestAuthorization();
        }
        return getResponse(request);
    }

    private static Response getResponse(Request request) throws IOException, NoSuchAlgorithmException {
        for (Route selection : Routes.routes()) {
            if (selection.respondsTo(request)) {
                return selection.call(request);
            }
        }
        return null;
    }

    private static Response requestAuthorization() {
        Map<String, String> header = new HashMap<>();
        header.put(ResponseHeader.WWW_AUTHENTICATE.header(), "Basic realm=\"Authentication required\"");
        return new Response(Status.UNAUTHORIZED.code(), header);
    }

}
