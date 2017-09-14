package com.pelensky.httpserver.Request;

import java.util.Map;
import java.util.regex.Pattern;


public class Request {

    private final String method;
    private final String uri;
    private final String httpVersion;
    private Map<String, String> headers;
    private String body;

    public Request(String method, String uri, String httpVersion, Map<String, String> headers, String body) {
        this.method = method;
        this.uri = uri;
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

    String getHttpVersion() {
        return httpVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String getFileType() {
        if (uri.contains(".")) {
            return uri.split(Pattern.quote("."))[1];
        }
        return null;
    }
}
