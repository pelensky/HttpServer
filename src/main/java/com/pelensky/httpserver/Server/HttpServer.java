package com.pelensky.httpserver.Server;

import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Socket.ServerSocketWrapper;
import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                        List<String> inputs = new ArrayList<>();
                        inputs.add(in.readLine());
                        String response = Response.findCommand(inputs);
                        out.println(response);
                        out.flush();
                        closeConnections(in, out);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
        );
    }

    private void closeConnections(BufferedReader in, PrintWriter out) throws IOException {
        out.close();
        in.close();
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
