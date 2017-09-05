package com.pelensky.httpserver;

import java.io.*;

public class FakeServerSocket implements ServerSocketWrapper {

    private final Integer port;
    private final String in;
    private String out;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    FakeServerSocket(Integer port, String in) {
        this.port = port;
        this.in = in;
    }

    @Override
    public SocketWrapper accept() throws IOException {
        return new SocketWrapper() {
            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(in.getBytes());
            }

            @Override
            public OutputStream getOutputStream() throws IOException {
                return byteArrayOutputStream;
            }

            @Override
            public void close() throws IOException {
                out = byteArrayOutputStream.toString();
            }
        };
    }

    @Override
    public void close() throws IOException {
    }

    public String getOut() {
        return out;
    }
}
