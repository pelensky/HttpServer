package com.pelensky.httpserver.RequestTypes;

public interface ResponseCommand {
    String execute(String input);
    boolean respondsTo(String input);
}
