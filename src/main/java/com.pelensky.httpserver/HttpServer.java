package com.pelensky.httpserver;

import java.io.*;
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
                       if (in.readLine().startsWith("GET /")) {
                           String twoHundredResponse = "HTTP/1.1 200 OK";
                           out.println(twoHundredResponse);
                       }
                       out.close();
                       in.close();
                       clientSocket.close();
                   } catch (IOException e) {
                       throw new UncheckedIOException(e);
                   }
               }
       );
    }

    void close() throws IOException {
        serverSocket.close();
    }

    Integer getPort() {
        return port;
    }

    String getDirectory() {
        return directory;
    }


}
