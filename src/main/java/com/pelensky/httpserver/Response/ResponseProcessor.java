package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.*;

public class ResponseProcessor {
private OutputStream out;

    public void sendResponse(SocketWrapper clientSocket, byte[] response) throws IOException {
        setUp(clientSocket);
        for (final byte responseByte : response) {
            out.write(responseByte);
        }
        tearDown();
    }

    private void setUp(SocketWrapper clientSocket) throws IOException {
     out = new BufferedOutputStream(clientSocket.getOutputStream());
    }

    private void tearDown() throws IOException {
        out.flush();
        out.close();
    }
}
