package com.pelensky.httpserver.RequestTypes;

import com.pelensky.httpserver.Request;
import com.pelensky.httpserver.Validations.Routes;
import com.pelensky.httpserver.Response.Status;

public class HeadCommand implements ResponseCommand {
    @Override
    public String execute(Request request) {
        return Routes.containsValidRoute(request.getUri()) ? Status.codes().get(200) : Status.codes().get(404);
    }

    @Override
    public boolean respondsTo(String input) {
        return (input.startsWith("HEAD"));
    }
}
