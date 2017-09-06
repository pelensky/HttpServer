package com.pelensky.httpserver.RequestTypes;

import Validations.Routes;
import com.pelensky.httpserver.Response.Status;

public class PutCommand implements ResponseCommand {
    @Override
    public String execute(String input) {
        return Routes.containsValidRoute(input) ? Status.codes().get(200) : Status.codes().get(404);
    }

    @Override
    public boolean respondsTo(String input) {
        return input.startsWith("PUT /");
    }
}
