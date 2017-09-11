package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Status;

public class Default implements Route {
    public String route() {
        return "/";
    }

    public String head() {
        return Status.codes().get(200);
    }
}
