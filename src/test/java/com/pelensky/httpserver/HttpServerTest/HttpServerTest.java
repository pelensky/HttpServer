package com.pelensky.httpserver.HttpServerTest;

import com.pelensky.httpserver.Server.HttpServer;
import com.pelensky.httpserver.Socket.FakeSocket;
import com.pelensky.httpserver.Utilities.FakeBasicAuthorization;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

public class HttpServerTest {

    @Test
    public void serverConnectsToClient() throws IOException, InterruptedException, NoSuchAlgorithmException {
        HttpServer server = new HttpServer(null, new FakeBasicAuthorization());
        FakeSocket fakeSocket = new FakeSocket();
        server.processRequestAndResponse(fakeSocket);
        assertTrue(fakeSocket.isConnected());
    }

}