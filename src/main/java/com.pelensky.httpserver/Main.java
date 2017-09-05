package com.pelensky.httpserver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandLineArgumentParser parser = new CommandLineArgumentParser(args);
        Integer port = parser.findPort();
        ServerSocketWrapper serverSocket = new HttpServerSocket(port);
        new HttpServer(port, parser.findDirectory(), serverSocket).serve();
    }
}
