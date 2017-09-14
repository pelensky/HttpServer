package com.pelensky.httpserver.Response;

import java.util.Map;

public class ResponseFormatter {

    public String format(Response response) {
        StringBuilder responseString = new StringBuilder();
        responseString.append(Status.codes().get(response.getStatusCode()));
        if (response.getResponseHeader() != null) formatHeaders(response, responseString);
        if (response.getBody() != null) formatContentLengthAndBody(response, responseString);
        return String.valueOf(responseString);
    }

    private void formatHeaders(Response response, StringBuilder responseString) {
        responseString.append(System.lineSeparator()).append(getHeaders(response));
    }

    private void formatContentLengthAndBody(Response response, StringBuilder responseString) {
        String body = response.getBody();
        String contentLength = "Content-Length: " + String.valueOf(getContentLength(body));
        responseString.append(System.lineSeparator()).append(contentLength).append(System.lineSeparator()).append(System.lineSeparator()).append(body);
    }

    private String getHeaders(Response response) {
        Map<String, String> responseHeaders = response.getResponseHeader();
        StringBuilder headers = new StringBuilder();
        responseHeaders.forEach((key, value) -> headers.append(key).append(": ").append(value).append(System.lineSeparator()));
        return String.valueOf(headers).trim();
    }

    private Integer getContentLength(String body) {
        return body.getBytes().length;
    }

}
