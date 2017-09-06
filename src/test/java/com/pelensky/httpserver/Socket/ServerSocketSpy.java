package com.pelensky.httpserver.Socket;

import com.pelensky.httpserver.Socket.FakeSocket;
import com.pelensky.httpserver.Socket.ServerSocketWrapper;
import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.*;

public class ServerSocketSpy implements ServerSocketWrapper {
    private Integer connections = 0;
    private boolean closed = false;

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

    public Integer getConnections() {
        return connections;
    }

    public boolean isClosed() {
        return closed;
    }
}
