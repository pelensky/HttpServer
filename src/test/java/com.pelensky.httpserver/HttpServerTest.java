package com.pelensky.httpserver;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class HttpServerTest {

    private HttpServer server;
    private Integer port = 1234;
    private String directory = "/Users/dan/Server/cob_spec/public/";
    private String host = "localhost";

    @Before
    public void setUp() {
        server = new HttpServer(port, directory);
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
        Socket client = new Socket(host, port);
        assertTrue(client.isConnected());
    }
}
