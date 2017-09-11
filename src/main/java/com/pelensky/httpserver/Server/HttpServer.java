package com.pelensky.httpserver.Server;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestProcessor;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.ResponseProcessor;
import com.pelensky.httpserver.Socket.ServerSocketWrapper;
import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.Executors;

class HttpServer {

  private final String directory;
  private final Integer port;
  private final ServerSocketWrapper serverSocket;
  private SocketWrapper clientSocket;
  private boolean inProgress = true;

  HttpServer(Integer port, String directory, ServerSocketWrapper serverSocket) {
    this.port = port;
    this.directory = directory;
    this.serverSocket = serverSocket;
  }

  void serve() {
    Executors.newSingleThreadExecutor()
        .execute(
            () -> {
                while (inProgress) {
                  try {
                    clientSocket = serverSocket.accept();
                    Request request = new RequestProcessor().createRequest(clientSocket);
                    String response = Response.getResponse(request);
                    new ResponseProcessor().sendResponse(clientSocket, response);
                    closeConnections();
                  } catch (IOException e) {
                    throw new UncheckedIOException(e);
                  }
                }
            });
  }

  private void closeConnections() throws IOException {
    clientSocket.close();
  }

  void closeServerSocket() throws IOException {
    serverSocket.close();
  }

  Integer getPort() {
    return port;
  }

  String getDirectory() {
    return directory;
  }

  void killServer() {
      inProgress = false;
  }
}
