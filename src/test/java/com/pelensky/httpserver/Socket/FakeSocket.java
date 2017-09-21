package com.pelensky.httpserver.Socket;

import java.io.*;

public class FakeSocket implements SocketWrapper {

    private final String input;
    private boolean connected = false;

    public FakeSocket(String input) {
        this.input = input;
    }

    public FakeSocket() {
        this.input = "Fake null null";
    }

    @Override
    public InputStream getInputStream() throws IOException {
        connected = true;
        return new ByteArrayInputStream(input.getBytes());
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new ByteArrayOutputStream();
    }

    @Override
    public void close() throws IOException {
    }

    public boolean isConnected() {
        return connected;
    }
}
