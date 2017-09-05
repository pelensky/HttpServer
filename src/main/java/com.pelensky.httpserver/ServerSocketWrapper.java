package com.pelensky.httpserver;

import java.io.IOException;

public interface ServerSocketWrapper {
    SocketWrapper accept() throws IOException;
    void close() throws IOException;
}

