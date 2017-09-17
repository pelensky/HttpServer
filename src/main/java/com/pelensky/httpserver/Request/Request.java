package com.pelensky.httpserver.Request;

import java.util.Map;


public class Request {

    private final String method;
    private final String uri;
    private final String fileType;
    private final String httpVersion;
    private final Map<String, String> headers;
    private final String body;

    public Request(String method, String uri, String fileType, String httpVersion, Map<String, String> headers, String body) {
        this.method = method;
        this.uri = uri;
        this.fileType = fileType;
        this.httpVersion = httpVersion;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getFileType() {
        return fileType;
    }
    String getHttpVersion() {
        return httpVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public boolean hasBody() {
        return body != null;
    }

}
