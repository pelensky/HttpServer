package com.pelensky.httpserver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class HttpServerTest {

    private HttpServer server;
    private Integer port = 5000;
    private String directory = "/Users/dan/Server/cob_spec/public/";

    @Before
    public void setUp() {
        server = new HttpServer(port, directory);
    }

    @Test
    public void httpServerHasHost() {
        assertEquals(server.getDirectory(), directory);
    }

    @Test
    public void httpServerHasPort() {
        assertEquals(server.getPort(), port);
    }
}
