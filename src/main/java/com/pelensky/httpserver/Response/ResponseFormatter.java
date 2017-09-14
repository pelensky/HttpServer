package com.pelensky.httpserver.Response;

import java.util.Map;

public class ResponseFormatter {

    public String format(Response response) {
        String httpVersion = "HTTP/1.1";
        StringBuilder responseString = new StringBuilder();
        responseString.append(httpVersion).append(" ").append(String.valueOf(response.getStatusCode()));
        formatHeaders(response, responseString);
        formatContentLengthAndBody(response, responseString);
        return String.valueOf(responseString);
    }

    private void formatHeaders(Response response, StringBuilder responseString) {
        if (response.getResponseHeader() != null ) responseString.append(System.lineSeparator()).append(getHeaders(response));
    }

    private void formatContentLengthAndBody(Response response, StringBuilder responseString) {
        if (response.getBody() != null) {
            String body = response.getBody();
            String contentLength = "Content-Length: " + String.valueOf(getContentLength(body));
            responseString.append(System.lineSeparator()).append(contentLength).append(System.lineSeparator()).append(System.lineSeparator()).append(body);
        }
    }

    private String getHeaders(Response response) {
        Map<String, String> responseHeaders = response.getResponseHeader();
        StringBuilder headers = new StringBuilder();
        responseHeaders.forEach((key, value) -> headers.append(key).append(": ").append(value).append(System.lineSeparator()));
        return String.valueOf(headers).trim(); //TODO Don't add a new line then remove it. ForEach with Index?
    }

    private Integer getContentLength(String body) {
        return body.getBytes().length;
    }

}
