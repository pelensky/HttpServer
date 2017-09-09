package com.pelensky.httpserver.RequestTypes;

import com.pelensky.httpserver.Request;
import com.pelensky.httpserver.Validations.Options;
import com.pelensky.httpserver.Validations.Routes;
import com.pelensky.httpserver.Response.Status;

public class OptionsCommand implements ResponseCommand {
    @Override
    public String execute(Request request) {
        if (Routes.containsValidRoute(request.getUri())) {
            return formatResponse(request);
        } else {
            return Status.codes().get(404);
        }
    }

    @Override
    public boolean respondsTo(String input) {
        return input.startsWith("OPTIONS");
    }

    private String formatResponse(Request request) {
        String statusCode = Status.codes().get(200);
        String options = Options.getOptions(request.getUri());
        return statusCode + System.lineSeparator() + "Allow: " + options;
    }
}
