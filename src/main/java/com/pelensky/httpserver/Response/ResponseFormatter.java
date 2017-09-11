package com.pelensky.httpserver.Response;

import java.util.Map;

public class ResponseFormatter {

    public String format(Response response) {
        String statusLine = Status.codes().get(response.getStatusCode());
        String responseHeaders = getHeaders(response);
        return statusLine + System.lineSeparator() + responseHeaders;
    }

    private String getHeaders(Response response) {
        if (response.getResponseHeader() != null) {
            Map<String, String> responseHeaders = response.getResponseHeader();
            StringBuilder headers = new StringBuilder();
            responseHeaders.forEach((key, value) -> headers.append(key).append(": ").append(value).append(System.lineSeparator()));
            return String.valueOf(headers);
        } else {
            return "";
        }
    }
}
