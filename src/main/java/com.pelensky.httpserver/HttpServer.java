package com.pelensky.httpserver;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

class HttpServer {

    private final String directory;
    private final Integer port;
    private ServerSocket serverSocket;
    private Socket socket;

    HttpServer(Integer port, String directory, ServerSocket serverSocket) {
        this.port = port;
        this.directory = directory;
        this.serverSocket = serverSocket;
    }

    void serve() {
        Executors.newSingleThreadExecutor().execute(() -> {
                    try {
                        socket = serverSocket.accept();
                        socket.close();
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
