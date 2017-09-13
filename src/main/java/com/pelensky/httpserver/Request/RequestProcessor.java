package com.pelensky.httpserver.Request;

import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestProcessor {

    public Request createRequest(SocketWrapper clientSocket) throws IOException {
        return new RequestParser().parseRequest(getRequestFromSocket(clientSocket));
    }

    String getRequestFromSocket(SocketWrapper clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String request = getHeader(in);
        if (request.contains("Content-Length")) {
            request += getBody(in, request);
        }
        return request;
    }

    private String getHeader(BufferedReader in) throws IOException {
        String line;
        StringBuilder request = new StringBuilder();
        while ( (line = in.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            } else {
                request.append(line);
                request.append(System.lineSeparator());
            }
        }
        return String.valueOf(request);
    }

    private String getBody(BufferedReader in, String request) throws IOException {
        String[] headerLines = request.split(System.lineSeparator());
        Integer contentLength = getContentLength(headerLines);
        return System.lineSeparator() + getBodyContent(in, contentLength);
    }

    private Integer getContentLength(String[] header) {
        Integer contentLength = 0;
        for (String line : header) {
            String[] lineWords = line.split(":");
            if (lineWords[0].trim().equals("Content-Length")) {
                contentLength = Integer.parseInt(lineWords[1].trim());
            }
        }
        return contentLength;
    }

    private String getBodyContent(BufferedReader in, Integer contentLength) throws IOException {
        char[] buffer = new char[contentLength];
        in.read(buffer, 0, contentLength);
        return String.valueOf(buffer);
    }

}
