package com.pelensky.httpserver.Response;

import java.util.Map;

public class ResponseFormatter {

    public String format(Response response) {
        String statusLine = Status.codes().get(response.getStatusCode());
        String responseHeaders;
        String contentLength;
        String body;
        StringBuilder responseString = new StringBuilder();
        responseString.append(statusLine).append(System.lineSeparator());
        if (response.getResponseHeader() != null) {
            responseHeaders = getHeaders(response);
            responseString.append(responseHeaders);
        }
        if (response.getBody() != null) {
            body = getBody(response);
            contentLength = "Content-Length: " + String.valueOf(getContentLength(body));
            responseString.append(contentLength).append(System.lineSeparator()).append(System.lineSeparator()).append(body);
        }
        return String.valueOf(responseString);
    }

    private String getHeaders(Response response) {
        Map<String, String> responseHeaders = response.getResponseHeader();
        StringBuilder headers = new StringBuilder();
        responseHeaders.forEach((key, value) -> headers.append(key).append(": ").append(value).append(System.lineSeparator()));
        return String.valueOf(headers);
    }

    private String getBody(Response response) {
        Map<String, String> bodyData = response.getBody();
        StringBuilder body = new StringBuilder();
        bodyData.forEach((key, value) ->body.append(key).append("=").append(value).append(System.lineSeparator()));
        return String.valueOf(body);
    }

    private Integer getContentLength(String body) {
        byte[] bytes = body.getBytes();
        return bytes.length;
    }
}
