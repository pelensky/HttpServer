package com.pelensky.httpserver.Server;

import com.pelensky.httpserver.Logs.LoggingTool;
import com.pelensky.httpserver.Socket.HttpServerSocket;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandLineArgumentParser parser = new CommandLineArgumentParser(args);
        Integer port = parser.findPort();
        LoggingTool.setUp();
        new HttpServer(new HttpServerSocket(port)).serve();
    }
}
