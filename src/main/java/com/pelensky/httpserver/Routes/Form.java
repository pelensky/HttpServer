package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Status;

public class Form implements Route{

    public String route() {
        return "/form";
    }
    public String post() {
        return Status.codes().get(200);
    }

    public String put() {
        return Status.codes().get(200);
    }
}
