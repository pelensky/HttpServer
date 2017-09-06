package com.pelensky.httpserver.Socket;

import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.*;

public class FakeSocket implements SocketWrapper {
    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream("Fake".getBytes());
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new ByteArrayOutputStream();
    }

    @Override
    public void close() throws IOException {
    }
}
