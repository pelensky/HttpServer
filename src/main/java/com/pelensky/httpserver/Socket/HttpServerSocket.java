package com.pelensky.httpserver.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerSocket implements ServerSocketWrapper {

    private final ServerSocket serverSocket;

    public HttpServerSocket(Integer port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public SocketWrapper accept() throws IOException {
        Socket client = serverSocket.accept();
        return new SocketWrapper() {
            @Override
            public InputStream getInputStream() throws IOException {
                return client.getInputStream();
            }

            @Override
            public OutputStream getOutputStream() throws IOException {
                return client.getOutputStream();
            }

            @Override
            public void close() throws IOException {
                client.close();
            }
        };
    }

    @Override
    public void close() throws IOException {
        serverSocket.close();
    }
}
