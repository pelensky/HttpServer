package com.pelensky.httpserver.Server;

import com.pelensky.httpserver.Utilities.LoggingTool;
import com.pelensky.httpserver.Socket.HttpServerSocket;
import com.pelensky.httpserver.Utilities.CommandLineArgumentParser;
import com.pelensky.httpserver.Utilities.Middleware;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandLineArgumentParser parser = new CommandLineArgumentParser(args);
        Integer port = parser.findPort();
        LoggingTool.setUp();
        Middleware middleware = new Middleware();
        middleware.add("logs", "admin", "hunter2");
        new HttpServer(new HttpServerSocket(port), middleware).serve();
    }
}
