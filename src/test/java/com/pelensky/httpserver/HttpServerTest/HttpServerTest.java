package com.pelensky.httpserver.HttpServerTest;

import com.pelensky.httpserver.Server.HttpServer;
import com.pelensky.httpserver.Socket.FakeSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

public class HttpServerTest {

    private HttpServer server;

    @Before
    public void setUp() throws IOException {
        server = new HttpServer(null);
    }

    @Test
    public void serverConnectsToClient() throws IOException, InterruptedException, NoSuchAlgorithmException {
        FakeSocket fakeSocket = new FakeSocket();
        server.processRequestAndResponse(fakeSocket);
        server.killServer();
        assertTrue(fakeSocket.isConnected());
    }

}