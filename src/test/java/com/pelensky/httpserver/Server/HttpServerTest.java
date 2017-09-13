package com.pelensky.httpserver.Server;

import com.pelensky.httpserver.Socket.FakeSocket;
import com.pelensky.httpserver.Socket.ServerSocketSpy;
import com.pelensky.httpserver.Socket.ServerSocketWrapper;
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
  public void serverConnectsToClient() throws IOException, InterruptedException {
    setUp(serverSocketSpy);
    server.serve();
    connect();
    server.killServer();
    assertTrue(serverSocketSpy.isConnected());
  }

  @Test
  public void serverSocketCanBeClosed() throws IOException, InterruptedException {
    setUp(serverSocketSpy);
    server.serve();
    server.closeServerSocket();
    assertTrue(serverSocketSpy.isClosed());
  }

  private void connect() throws InterruptedException, IOException {
    FakeSocket fakeSocket = new FakeSocket();
    Thread.sleep(100);
    fakeSocket.close();
    server.killServer();
  }
}
