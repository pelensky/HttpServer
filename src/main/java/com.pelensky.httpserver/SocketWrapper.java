package com.pelensky.httpserver;

import java.io.IOException;

public interface SocketWrapper {
    void close() throws IOException;
}
