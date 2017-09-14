package com.pelensky.httpserver.Request;

import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    
    private String request;
    private String requestLine;
    private Map<String, String> headers;
    private String body;
    private String method;
    private String uri;
    private String httpVersion;
    private Integer bodyBegins;
    
    public Request parseRequest(String request) {
        this.request = request;
        splitRequest();
        splitRequestLineComponents();
        return new Request(method, uri, httpVersion, headers, body);
    }

    private void splitRequest() {
        String[] splitRequest = request.split(System.lineSeparator());
        requestLine = splitRequest[0];
        extractHeaders(splitRequest);
        if (bodyBegins != null) {
            body = extractBody(splitRequest);
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

    private String extractBody(String[] splitRequest) {
        StringBuilder body = new StringBuilder();
        for (int i = bodyBegins + 1; i < splitRequest.length; i++) {
            body.append(splitRequest[i]);
        }
        return String.valueOf(body);
    }

    private void splitRequestLineComponents() {
        String[] splitRequestLine = requestLine.split(" ");
        method = splitRequestLine[0];
        uri = splitRequestLine[1];
        httpVersion = splitRequestLine[2];
    }


}
