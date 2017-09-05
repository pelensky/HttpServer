package com.pelensky.httpserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class HttpServerTest {

    private HttpServer server;
    private final Integer port = 1234;
    private final String directory = "/Users/dan/Server/cob_spec/public/";
    private final String host = "localhost";
    private ServerSocketSpy serverSocketSpy;

    @Before
    public void setUp() throws IOException {
        serverSocketSpy = new ServerSocketSpy(port);
        server = new HttpServer(port, directory, serverSocketSpy);
    }

    @Test
    public void serverHasHost() {
        assertEquals(server.getDirectory(), directory);
    }

    @Test
    public void serverHasPort() {
        assertEquals(server.getPort(), port);
    }

    @Test
    public void serverConnectsToClient() throws IOException {
        server.serve();
        assertTrue(serverSocketSpy.isConnectionAccepted());
    }
}
