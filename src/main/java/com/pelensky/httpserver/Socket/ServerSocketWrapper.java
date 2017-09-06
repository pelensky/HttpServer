package com.pelensky.httpserver.Socket;

import java.io.IOException;

public interface ServerSocketWrapper {
    SocketWrapper accept() throws IOException;
    void close() throws IOException;
}

