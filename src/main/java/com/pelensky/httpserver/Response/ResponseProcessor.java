package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ResponseProcessor {
    private OutputStreamWriter out;

    public void sendResponse(SocketWrapper clientSocket, byte[] response) throws IOException {
        setUp(clientSocket);
        for (final byte responseByte : response) {
            out.write(responseByte);
        }
        tearDown();
    }

    private void setUp(SocketWrapper clientSocket) throws IOException {
        out = new OutputStreamWriter(clientSocket.getOutputStream());
    }

    private void tearDown() throws IOException {
        out.flush();
        out.close();
    }
}
