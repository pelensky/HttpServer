package com.pelensky.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerSocket implements ServerSocketWrapper{

    private final ServerSocket serverSocket;

    HttpServerSocket(Integer port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public SocketWrapper accept() throws IOException {
        Socket client = serverSocket.accept();
        return client::close;
    }
}
