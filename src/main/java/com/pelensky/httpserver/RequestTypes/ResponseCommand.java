package com.pelensky.httpserver.RequestTypes;

import com.pelensky.httpserver.Request;

public interface ResponseCommand {
    String execute(Request request);
    boolean respondsTo(String method);
}
