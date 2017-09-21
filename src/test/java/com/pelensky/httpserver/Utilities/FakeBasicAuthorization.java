package com.pelensky.httpserver.Utilities;

import com.pelensky.httpserver.Request.Request;

public class FakeBasicAuthorization extends BasicAuthorization {
    public boolean isAuthenticationProvided(Request request){
        return false;
    }
}
