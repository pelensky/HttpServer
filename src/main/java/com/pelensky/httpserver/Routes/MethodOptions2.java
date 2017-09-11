package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Status;

public class MethodOptions2 implements Route {

    public String route() {
        return "/method_options2";
    }

    public String optionsCode() {
        return Status.codes().get(200);
    }
}
