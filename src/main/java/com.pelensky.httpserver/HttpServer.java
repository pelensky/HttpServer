package com.pelensky.httpserver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

class HttpServer {

    private final String directory;
    private final Integer port;
    private final ServerSocketWrapper serverSocket;
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
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                        List<String> inputs = new ArrayList<>();
                        inputs.add(in.readLine());
                        String response = Response.findCommand(inputs);
                        out.println(response);
                        out.flush();
                        inputs.clear();
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

    void closeServerSocket() throws IOException {
        serverSocket.close();
    }

    Integer getPort() {
        return port;
    }

    String getDirectory() {
        return directory;
    }


}
