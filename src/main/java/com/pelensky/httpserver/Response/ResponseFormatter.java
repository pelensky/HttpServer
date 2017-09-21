package com.pelensky.httpserver.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ResponseFormatter {

    public byte[] formatResponse(Response response) throws IOException {
        return combineHeaderWithBody(response, formatStatusCodeAndHeaders(response));
    }

    private String formatStatusCodeAndHeaders(Response response) {
        final String httpVersion = "HTTP/1.1";
        StringBuilder headerString = new StringBuilder();
        headerString.append(httpVersion).append(" ").append(String.valueOf(response.getStatusCode()));
        addHeaders(response, headerString);
        addContentLength(response, headerString);
        return String.valueOf(headerString);
    }

    private byte[] combineHeaderWithBody(Response response, String statusCodeAndHeaders) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(statusCodeAndHeaders.getBytes());
        outputStream.write(response.getBody());
        return outputStream.toByteArray();
    }

    private void addHeaders(Response response, StringBuilder responseString) {
        if (response.getResponseHeader() != null ) responseString.append(System.lineSeparator()).append(formatHeaders(response));
    }

    private void addContentLength(Response response, StringBuilder responseString) {
        if (!response.isBodyEmpty()) {
            String contentLength = ResponseHeader.CONTENT_LENGTH.header() + String.valueOf(response.getBody().length);
            responseString.append(System.lineSeparator()).append(contentLength).append(System.lineSeparator()).append(System.lineSeparator());
        }
    }

    private String formatHeaders(Response response) {
        StringBuilder headers = new StringBuilder();
        response.getResponseHeader().forEach((key, value) -> headers.append(key).append(": ").append(value).append(System.lineSeparator()));
        return String.valueOf(headers).trim();
    }

}
