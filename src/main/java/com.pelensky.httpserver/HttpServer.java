package com.pelensky.httpserver;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.Executors;

class HttpServer {

    private final String directory;
    private final Integer port;
    private ServerSocketWrapper serverSocket;
    private SocketWrapper clientSocket;

    HttpServer(Integer port, String directory, ServerSocketWrapper serverSocket) {
        this.port = port;
        this.directory = directory;
        this.serverSocket = serverSocket;
    }

    void serve() {
        Executors.newSingleThreadExecutor().execute(() -> {
                    try {
                        clientSocket = serverSocket.accept();
                        clientSocket.close();
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
