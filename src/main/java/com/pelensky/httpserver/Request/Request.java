package com.pelensky.httpserver.Request;

import com.pelensky.httpserver.Utilities.Cookies;

import java.util.Map;

public class Request {

    private final String method;
    private final String uri;
    private final String fileType;
    private final String httpVersion;
    private final Map<String, String> headers;
    private final String body;
    private final Map<String, String> parameters;

    public Request(String method, String uri, String fileType, String httpVersion, Map<String, String> headers, String body, Map<String, String> parameters) {
        this.method = method;
        this.uri = uri;
        this.fileType = fileType;
        this.httpVersion = httpVersion;
        this.headers = headers;
        this.body = body;
        this.parameters = parameters;
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

    boolean hasHeaders() {
        return !headers.isEmpty();
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public boolean hasBody() {
        return body != null;
    }

    public String getBody() {
        return body;
    }

    public boolean hasParameters() {
        return parameters != null;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String findETag() {
        return headers.get(RequestHeader.IF_MATCH.header());
    }

    public String findFileName() {
        return uri + "." + fileType;
    }

    public boolean isAFile() {
        return getFileType() != null;
    }

    public boolean hasCookies() {
        return headers.containsKey(RequestHeader.COOKIE.header());
    }

    public Map<String,String> findCookies() {
        return (hasCookies()) ? new Cookies().getCookie(headers) : null;
    }

    public boolean hasRange() {
        return headers.containsKey(RequestHeader.RANGE.header());
    }
}
