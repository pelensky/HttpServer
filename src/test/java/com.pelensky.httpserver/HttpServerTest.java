package com.pelensky.httpserver;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HttpServerTest {

    private HttpServer server;
    private final Integer port = 1234;
    private final String directory = "/Users/dan/Server/cob_spec/public/";
    private final ServerSocketSpy serverSocketSpy = new ServerSocketSpy(port);

    public void setUp(ServerSocketWrapper serverSocket) throws IOException {
        server = new HttpServer(port, directory, serverSocket);
    }

    @Test
    public void serverHasHost() throws IOException {
        setUp(serverSocketSpy);
        assertEquals(server.getDirectory(), directory);
    }

    @Test
    public void serverHasPort() throws IOException {
        setUp(serverSocketSpy);
        assertEquals(server.getPort(), port);
    }

    @Test
    public void serverConnectsToClient() throws IOException, InterruptedException {
        setUp(serverSocketSpy);
        server.serve();
        connect();
        assertEquals(1, serverSocketSpy.getConnections(), 0);
    }

    @Test
    public void serverSocketCanBeClosed() throws IOException, InterruptedException {
        setUp(serverSocketSpy);
        server.serve();
        server.close();
        assertTrue(serverSocketSpy.isClosed());
    }

    @Test
    public void serverRespondsToGetRequestWith200() throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = new FakeServerSocket(port, "GET / HTTP/1.1");
        setUp(fakeServerSocket);
        server.serve();
        connect();
        String twoHundredResponse = "HTTP/1.1 200 OK\n";
        assertEquals(fakeServerSocket.getOut(), twoHundredResponse);
    }

    @Test
    public void serverRespondsToPostRequestWith200() throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = new FakeServerSocket(port, "POST /form HTTP/1.1");
        setUp(fakeServerSocket);
        server.serve();
        connect();
        String twoHundredResponse = "HTTP/1.1 200 OK\n";
        assertEquals(fakeServerSocket.getOut(), twoHundredResponse);
    }

    private void connect() throws InterruptedException, IOException {
        FakeSocket fakeSocket = new FakeSocket();
        Thread.sleep(100);
        fakeSocket.close();
    }
}
