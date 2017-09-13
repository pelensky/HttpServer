package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.IOException;
import java.io.PrintWriter;

public class ResponseProcessor {
    private PrintWriter out;

    public void sendResponse(SocketWrapper clientSocket, String response) throws IOException {
        setUp(clientSocket);
        out.println(response);
        tearDown();
    }

    private void setUp(SocketWrapper clientSocket) throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    private void tearDown() {
        out.flush();
        out.close();
    }
}
