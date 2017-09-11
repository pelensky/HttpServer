package com.pelensky.httpserver.Socket;

import java.io.IOException;

public class ServerSocketSpy implements ServerSocketWrapper {
    private boolean connected = false;
    private boolean closed = false;

    @Override
    public SocketWrapper accept() throws IOException {
        connected = true;
        closed = true;
        return new FakeSocket();
    }

    @Override
    public void close() {
        closed = true;
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isClosed() {
        return closed;
    }
}
