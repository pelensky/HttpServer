package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Status;

public class Redirect implements Route {

    public String route() {
        return "/redirect";
    }

    public String get() {
        return Status.codes().get(302) + System.lineSeparator() + "Location: /";
    }
}
