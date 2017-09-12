package com.pelensky.httpserver.Response;

import java.util.Map;

public class ResponseFormatter {

    public String format(Response response) {
        String statusLine = Status.codes().get(response.getStatusCode());
        String responseHeaders;
        String contentLength;
        String body;
        StringBuilder responseString = new StringBuilder();
        responseString.append(statusLine);
        if (response.getResponseHeader() != null) {
            responseHeaders = getHeaders(response);
            responseString.append(System.lineSeparator()).append(responseHeaders);
        }
        if (response.getBody() != null) {
            body = response.getBody();
            contentLength = "Content-Length: " + String.valueOf(getContentLength(body));
            responseString.append(System.lineSeparator()).append(contentLength).append(System.lineSeparator()).append(System.lineSeparator()).append(body);
        }
        return String.valueOf(responseString).trim()+ System.lineSeparator();
    }

    private String getHeaders(Response response) {
        Map<String, String> responseHeaders = response.getResponseHeader();
        StringBuilder headers = new StringBuilder();
        responseHeaders.forEach((key, value) -> headers.append(key).append(": ").append(value).append(System.lineSeparator()));
        return String.valueOf(headers);
    }

    private Integer getContentLength(String body) {
        byte[] bytes = body.getBytes();
        return bytes.length;
    }
}
