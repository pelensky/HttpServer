package com.pelensky.httpserver.RequestTypes;

import Validations.Options;
import Validations.Routes;
import com.pelensky.httpserver.Response.Status;

public class OptionsCommand implements ResponseCommand {
    @Override
    public String execute(String input) {
        if (Routes.containsValidRoute(input)) {
            return formatResponse(input);
        } else {
            return Status.codes().get(404);
        }
    }

    @Override
    public boolean respondsTo(String input) {
        return input.startsWith("OPTIONS");
    }

    private String formatResponse(String input) {
        String route = Routes.getRoute(input);
        String statusCode = Status.codes().get(200);
        String options = Options.getOptions(route);
        return statusCode + "\n" + "Allow: " + options;
    }
}
