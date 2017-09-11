package com.pelensky.httpserver.Request;

import java.util.Map;


public class Request {

    private final String method;
    private final String uri;
    private final String httpVersion;
    private Map<String, String> headers;
    private Map <String, String> body;

    Request(String method, String uri, String httpVersion, Map<String, String> headers, Map<String, String> body) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
        this.headers = headers;
        this.body = body;
    }

    Request(String method, String uri, String httpVersion) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
    }

    Request(String method, String uri, String httpVersion, Map<String, String> headers) {
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
