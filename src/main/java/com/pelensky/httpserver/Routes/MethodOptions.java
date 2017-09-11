package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Status;

public class MethodOptions implements Route {

    public String route() {
        return "/method_options";
    }

    public String head() {
        return Status.codes().get(200);
    }

    public String  post() {
        return Status.codes().get(200);
    }

    public String optionsCode() {
        return Status.codes().get(200);
    }

    public String put() {
        return Status.codes().get(200);
    }
}
