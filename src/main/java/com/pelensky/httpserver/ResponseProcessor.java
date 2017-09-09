package com.pelensky.httpserver;

import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.IOException;
import java.io.PrintWriter;

public class ResponseProcessor {

    public void sendResponse(SocketWrapper clientSocket, String response) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
        out.println(response);
        out.flush();
        out.close();
    }
}
