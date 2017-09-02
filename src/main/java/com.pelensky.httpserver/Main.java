package com.pelensky.httpserver;

public class Main {
    public static void main(String[] args) {
        CommandLineArgumentParser parser = new CommandLineArgumentParser(args);
        new HttpServer(parser.findPort(), parser.findDirectory()).serve();
    }
}
