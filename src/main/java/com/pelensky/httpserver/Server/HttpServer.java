package com.pelensky.httpserver.Server;

import com.pelensky.httpserver.RequestProcessor;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Socket.ServerSocketWrapper;
import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.*;
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
                        String input = new RequestProcessor().getRequest(clientSocket);
                        String response = Response.findCommand(input);
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                        out.println(response);
                        out.flush();
                        closeConnections(out);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
        );
    }

    private void closeConnections(PrintWriter out) throws IOException {
        out.close();
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
