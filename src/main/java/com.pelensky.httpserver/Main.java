package com.pelensky.httpserver;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandLineArgumentParser parser = new CommandLineArgumentParser(args);
        Integer port = parser.findPort();
        ServerSocket serverSocket = new ServerSocket(port);
        new HttpServer(port, parser.findDirectory(), serverSocket).serve();
    }
}
