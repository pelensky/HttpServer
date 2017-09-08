package com.pelensky.httpserver.Socket;

import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.*;

public class FakeSocket implements SocketWrapper {

    private String input;

    public FakeSocket(String input) {
        this.input = input;
    }

    public FakeSocket() {
        this.input = "Fake";
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(input.getBytes());
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new ByteArrayOutputStream();
    }

    @Override
    public void close() throws IOException {
    }
}
