package com.pelensky.httpserver;

class HttpServer {

    private final String directory;
    private final Integer port;

    HttpServer(Integer port, String directory) {
        this.port = port;
        this.directory = directory;
    }

    Integer getPort() {
        return port;
    }

    String getDirectory() {
        return directory;
    }
}
