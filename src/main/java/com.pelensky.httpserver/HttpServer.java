package com.pelensky.httpserver;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

class HttpServer {

    private final String directory;
    private final Integer port;
    private ServerSocket serverSocket;

    HttpServer(Integer port, String directory) {
        this.port = port;
        this.directory = directory;
    }

    void serve() {
        Executors.newSingleThreadExecutor().execute(() -> {
                    try {
                        serverSocket = new ServerSocket(port);
                        serverSocket.accept();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
        );
    }

    Integer getPort() {
        return port;
    }

    String getDirectory() {
        return directory;
    }
}
