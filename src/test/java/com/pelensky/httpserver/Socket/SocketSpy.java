package com.pelensky.httpserver.Socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketSpy implements SocketWrapper {

    private Boolean wasCalled = false;

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        wasCalled = true;
        return new ByteArrayOutputStream();
    }

    @Override
    public void close() throws IOException {
    }

    public boolean getWasCalled() {
        return wasCalled;
    }
}
