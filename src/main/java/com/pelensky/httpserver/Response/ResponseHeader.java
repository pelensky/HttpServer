package com.pelensky.httpserver.Response;

public enum ResponseHeader {
    CONTENT_LENGTH("Content-Length: "),
    COOKIE("Set-Cookie"),
    LOCATION("Location"),
    WWW_AUTHENTICATE("WWW-Authenticate");

    private final String header;

    ResponseHeader(String header){
        this.header = header;
    }

    public String header() {
        return header;
    }
}
