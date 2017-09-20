package com.pelensky.httpserver.Request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    private Map<String, String> parameters;

    public Request parseRequest(String request) throws UnsupportedEncodingException {
        this.request = request;
        splitRequest();
        splitRequestLineComponents();
        return new Request(method, uri, fileType, httpVersion, headers, body, parameters);
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
        for (int i = 1; i < splitRequest.length; i++) {
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

    private void splitRequestLineComponents() throws UnsupportedEncodingException {
        String[] splitRequestLine = requestLine.split(" ");
        parameters = findParameters(splitRequestLine[1]);
        method = splitRequestLine[0];
        uri = getUri(splitRequestLine);
        httpVersion = splitRequestLine[2];
    }

    private String getUri(String[] splitRequestLine) {
        String route = (splitRequestLine[1].length() > 0) ? splitRequestLine[1].substring(1) : "";
        if (route.contains(".")) {
            String[] splitRoute = route.split(Pattern.quote("."));
            route = splitRoute[0];
            fileType = splitRoute[1];
        }
        if (route.contains("?")) {
            String[] splitRoute = route.split(Pattern.quote("?"));
            route = splitRoute[0];
        }
        return route;
    }

    private Map<String, String> findParameters(String uriAndParams) throws UnsupportedEncodingException {
        if (uriAndParams.contains("?")) {
            Map<String, String> parameters = new HashMap<>();
            String params = uriAndParams.split("\\?")[1];
            if (params.contains("&")) {
                gatherMultipleParameters(parameters, params);
            } else {
                gatherParameters(parameters, params);
            }
            return parameters;
        }
        return null;
    }

    private void gatherMultipleParameters(Map<String, String> parameters, String params) throws UnsupportedEncodingException {
        String[] splitAtAnd = params.split("&");
        for (String pair : splitAtAnd) {
            gatherParameters(parameters, pair);
        }
    }

    private void gatherParameters(Map<String, String> parameters, String params) throws UnsupportedEncodingException {
        String[] splitParams = params.split("=");
        parameters.put(splitParams[0], URLDecoder.decode(splitParams[1], "UTF-8"));
    }


}
