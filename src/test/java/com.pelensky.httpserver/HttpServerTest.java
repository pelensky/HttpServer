package com.pelensky.httpserver;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HttpServerTest {

    private HttpServer server;
    private final Integer port = 1234;
    private final String directory = "/Users/dan/Server/cob_spec/public/";
    private final ServerSocketSpy serverSocketSpy = new ServerSocketSpy();

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
        server.closeServerSocket();
        assertTrue(serverSocketSpy.isClosed());
    }

    @Test
    public void serverRespondsToGetRequestWith200() throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = setUpResponseTest("GET / HTTP/1.1\n");
        assertEquals( "HTTP/1.1 200 OK\n", fakeServerSocket.getOut());
    }

    @Test
    public void serverRespondsToUnknownGetRouteWith404() throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = setUpResponseTest("GET /foobar HTTP/1.1\n");
        assertEquals( "HTTP/1.1 404 Not Found\n", fakeServerSocket.getOut());
    }

    @Test
    public void serverRespondsToPostRequestWith200() throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = setUpResponseTest("POST /form HTTP/1.1\nContent-Length: 348\n\n\"My\"=\"Data\"\n");
        assertEquals( "HTTP/1.1 200 OK\n", fakeServerSocket.getOut());
    }

    @Test
    public void serverRespondsToUnknownPostRouteWith404() throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = setUpResponseTest("POST /foobar HTTP/1.1\n");
        assertEquals( "HTTP/1.1 404 Not Found\n", fakeServerSocket.getOut());
    }

    @Test
    public void serverRespondsToPutRequestWith200() throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = setUpResponseTest("PUT /form HTTP/1.1\n");
        assertEquals( "HTTP/1.1 200 OK\n", fakeServerSocket.getOut());
    }

    @Test
    public void serverRespondsToUnknownPutRouteWith404() throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = setUpResponseTest("PUT /foobar HTTP/1.1\n");
        assertEquals( "HTTP/1.1 404 Not Found\n", fakeServerSocket.getOut());
    }

    @Test
    public void serverRespondsToHeadRequestWith200() throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = setUpResponseTest("HEAD / HTTP/1.1\n");
        server.closeServerSocket();
        assertEquals( "HTTP/1.1 200 OK\n", fakeServerSocket.getOut());
    }

    @Test
    public void serverRespondsToUnknownHeadRouteWith404() throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = setUpResponseTest("HEAD /foobar HTTP/1.1\n");
        server.closeServerSocket();
        assertEquals( "HTTP/1.1 404 Not Found\n", fakeServerSocket.getOut());
    }

    private FakeServerSocket setUpResponseTest(String in) throws IOException, InterruptedException {
        FakeServerSocket fakeServerSocket = new FakeServerSocket(in);
        setUp(fakeServerSocket);
        server.serve();
        connect();
        return fakeServerSocket;
    }

    private void connect() throws InterruptedException, IOException {
        FakeSocket fakeSocket = new FakeSocket();
        Thread.sleep(100);
        fakeSocket.close();
    }
}
