package com.pelensky.httpserver.Utilities;

import com.pelensky.httpserver.Request.Request;

public class FakeMiddleware extends Middleware {
    public boolean isAuthenticationProvided(Request request){
        return false;
    }
}
