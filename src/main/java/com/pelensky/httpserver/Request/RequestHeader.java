package com.pelensky.httpserver.Request;

public enum RequestHeader {
    AUTHORIZATION("Authorization"),
    COOKIE("Cookie"),
    CONTENT_LENGTH("Content-Length"),
    IF_MATCH("If-Match"),
    RANGE("Range");

    private final String header;

    RequestHeader(String header){
        this.header = header;
    }

    public String header() {
        return header;
    }
}
