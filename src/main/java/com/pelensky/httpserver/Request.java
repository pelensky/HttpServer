package com.pelensky.httpserver;

import java.util.Map;


public class Request {

    private String method;
    private String uri;
    private String httpVersion;
    private Map<String, String> headers;
    private Map <String, String> body;

    public Request(String method, String uri, String httpVersion, Map<String, String> headers, Map<String, String> body) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
        this.headers = headers;
        this.body = body;
    }

    public Request(String method, String uri, String httpVersion) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
    }

    public Request(String method, String uri, String httpVersion, Map<String, String> headers) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    String getHttpVersion() {
        return httpVersion;
    }

    Map<String, String> getHeaders() {
        return headers;
    }

    Map<String,String> getBody() {
        return body;
    }
}
