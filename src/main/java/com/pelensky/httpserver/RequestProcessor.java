package com.pelensky.httpserver;

import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestProcessor {

    public String getRequest(SocketWrapper clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String line;
        StringBuilder request = new StringBuilder();
        while ( (line = in.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            } else {
                request.append(line);
                request.append("\n");
            }
        }
        return String.valueOf(request);
    }
}
