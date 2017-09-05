package com.pelensky.httpserver;

import java.io.IOException;

public class ServerSocketSpy implements ServerSocketWrapper{
    private boolean connectionAccepted = false;
    private Integer port;

    ServerSocketSpy(Integer port) {
        this.port = port;
    }

    @Override
    public SocketWrapper accept() throws IOException {
        connectionAccepted = true;
        return () -> {
        };
    }

    boolean isConnectionAccepted() {
        return connectionAccepted;
    }
}
