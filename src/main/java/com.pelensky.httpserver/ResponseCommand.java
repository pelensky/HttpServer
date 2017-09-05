package com.pelensky.httpserver;

public interface ResponseCommand {
    String execute();
    boolean respondsTo(String verb);
}
