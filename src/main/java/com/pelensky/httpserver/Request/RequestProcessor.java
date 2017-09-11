package com.pelensky.httpserver.Request;

import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestProcessor {

    public Request createRequest(SocketWrapper clientSocket) throws IOException {
        return new RequestParser(getRequestFromSocket(clientSocket)).parseRequest();
    }

    public String getRequestFromSocket(SocketWrapper clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
}
