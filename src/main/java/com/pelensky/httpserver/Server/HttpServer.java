package com.pelensky.httpserver.Server;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestProcessor;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Router.Router;
import com.pelensky.httpserver.Response.ResponseFormatter;
import com.pelensky.httpserver.Response.ResponseProcessor;
import com.pelensky.httpserver.Socket.ServerSocketWrapper;
import com.pelensky.httpserver.Socket.SocketWrapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.Executors;

class HttpServer {

  private final ServerSocketWrapper serverSocket;
  private SocketWrapper clientSocket;
  private boolean inProgress = true;

  HttpServer(ServerSocketWrapper serverSocket) {
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
                          Response response = Router.findResponse(request);
                          new ResponseProcessor().sendResponse(clientSocket, new ResponseFormatter().format(response));
                          closeConnections();
                        } catch (IOException e) {
                          throw new UncheckedIOException(e);
                        }
                      }
                    }
            );
  }

  private void closeConnections() throws IOException {
    clientSocket.close();
  }

  void closeServerSocket() throws IOException {
    serverSocket.close();
  }

  void killServer() {
    inProgress = false;
  }
}
