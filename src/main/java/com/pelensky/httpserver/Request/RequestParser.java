package com.pelensky.httpserver.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RequestParser {
    
    private String request;
    private String requestLine;
    private Map<String, String> headers;
    private String body;
    private String method;
    private String uri;
    private String httpVersion;
    private String fileType;
    private Integer bodyBegins;
    
    public Request parseRequest(String request) {
        this.request = request;
        splitRequest();
        splitRequestLineComponents();
        return new Request(method, uri, fileType, httpVersion, headers, body);
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
        getUri(splitRequestLine);
        httpVersion = splitRequestLine[2];
    }

    private String getUri(String[] splitRequestLine) {
        uri = (splitRequestLine[1].length() > 0) ? splitRequestLine[1].substring(1) : "";
        if (uri.contains(".")) {
            String[] splitRoute = uri.split(Pattern.quote("."));
            uri = splitRoute[0];
            fileType = splitRoute[1];
        }
        return uri;
    }


}
