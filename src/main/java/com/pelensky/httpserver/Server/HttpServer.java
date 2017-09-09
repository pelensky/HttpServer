package com.pelensky.httpserver.Server;

import com.pelensky.httpserver.Request;
import com.pelensky.httpserver.RequestProcessor;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.ResponseProcessor;
import com.pelensky.httpserver.Socket.ServerSocketWrapper;
import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.Executors;

public class HttpServer {

    private final String directory;
    private final Integer port;
    private final ServerSocketWrapper serverSocket;
    private SocketWrapper clientSocket;

    public HttpServer(Integer port, String directory, ServerSocketWrapper serverSocket) {
        this.port = port;
        this.directory = directory;
        this.serverSocket = serverSocket;
    }

    public void serve() {
        Executors.newSingleThreadExecutor().execute(() -> {
                    try {
                        clientSocket = serverSocket.accept();
                        Request request = new RequestProcessor().createRequest(clientSocket);
                        String response = Response.findCommand(request);
                        new ResponseProcessor().sendResponse(clientSocket, response);
                        closeConnections();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
        );
    }

    private void closeConnections() throws IOException {
        clientSocket.close();
    }

    public void closeServerSocket() throws IOException {
        serverSocket.close();
    }

    public Integer getPort() {
        return port;
    }

    public String getDirectory() {
        return directory;
    }


}
