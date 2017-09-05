package com.pelensky.httpserver;

import java.io.*;

public class ServerSocketSpy implements ServerSocketWrapper{
    private Integer connections = 0;
    private boolean closed = false;
    private Integer port;

    ServerSocketSpy(Integer port) {
        this.port = port;
    }

    @Override
    public SocketWrapper accept() throws IOException {
        connections ++;
        closed = true;
        return new FakeSocket();
    }

    @Override
    public void close() {
        closed = true;
    }

    Integer getConnections() {
        return connections;
    }

    boolean isClosed() {
        return closed;
    }
}
