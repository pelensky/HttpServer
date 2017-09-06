package com.pelensky.httpserver.Server;

import com.pelensky.httpserver.Socket.HttpServerSocket;
import com.pelensky.httpserver.Socket.ServerSocketWrapper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandLineArgumentParser parser = new CommandLineArgumentParser(args);
        Integer port = parser.findPort();
        ServerSocketWrapper serverSocket = new HttpServerSocket(port);
        HttpServer httpServer = new HttpServer(port, parser.findDirectory(), serverSocket);
        while (true) {
            httpServer.serve();
        }
    }
}
