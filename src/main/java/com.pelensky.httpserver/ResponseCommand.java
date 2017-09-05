package com.pelensky.httpserver;

public interface ResponseCommand {
    String execute(String input);
    boolean respondsTo(String input);
}
