package com.pelensky.httpserver;

import java.util.*;

public class RequestParser {
    
    private final String request;
    private String requestLine;
    private Map<String, String> headers;
    private Map<String, String> body;
    private String method;
    private String uri;
    private String httpVersion;
    private Integer bodyBegins;
    
    public RequestParser(String request) {
        this.request = request;
    }

    Request parseRequest() {
        splitRequest();
        splitRequestLineComponents();
        return new Request(method, uri, httpVersion, headers, body);
    }

    private void splitRequest() {
        String[] splitRequest = request.split(System.lineSeparator());
        requestLine = splitRequest[0];
        extractHeaders(splitRequest);
        if (bodyBegins != null) {
            extractBody(splitRequest);
        }
    }

    private void extractHeaders(String[] splitRequest) {
        headers = new HashMap<>();
        for (int i = 1; i < splitRequest.length; i++ ){
            if (splitRequest[i].isEmpty()) {
                bodyBegins = i;
                break;
            } else {
                String[] splitHeader = splitRequest[i].split(": ");
                headers.put(splitHeader[0], splitHeader[1]);
            }
        }
    }

    private void extractBody(String[] splitRequest) {
        body = new HashMap<>();
        for (int i = bodyBegins + 1; i < splitRequest.length; i++) {
            String[] splitBody = splitRequest[i].split("[&]");
            for (String all : splitBody) {
                String[] splitBodyComponent = all.split("[=]");
                body.put(splitBodyComponent[0], splitBodyComponent[1]);
            }
        }
    }

    private void splitRequestLineComponents() {
        String[] splitRequestLine = requestLine.split(" ");
        method = splitRequestLine[0];
        uri = splitRequestLine[1];
        httpVersion = splitRequestLine[2];
    }

    String getMethod() {
        return method;
    }

    String getUri() {
        return uri;
    }

    String getHttpVersion() {
        return httpVersion;
    }

    String getRequestLine() {
        return requestLine;
    }

    Map<String, String> getHeaders() {
        return headers;
    }

    Map<String, String> getBody() {
        return body;
    }

}
