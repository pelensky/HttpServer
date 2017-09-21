package com.pelensky.httpserver.Server;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestProcessor;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.ResponseFormatter;
import com.pelensky.httpserver.Response.ResponseProcessor;
import com.pelensky.httpserver.Router.Router;
import com.pelensky.httpserver.Socket.ServerSocketWrapper;
import com.pelensky.httpserver.Socket.SocketWrapper;
import com.pelensky.httpserver.Utilities.LoggingTool;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

  private final ServerSocketWrapper serverSocket;
  private final ExecutorService executor = Executors.newFixedThreadPool(10);

  private boolean inProgress = true;

  public HttpServer(ServerSocketWrapper serverSocket) {
    this.serverSocket = serverSocket;
  }

  void serve() throws IOException {
    while (inProgress) {
      SocketWrapper clientSocket = serverSocket.accept();
      executor.execute(
            () -> {
                try {
                  processRequestAndResponse(clientSocket);
                } catch (IOException e) {
                    LoggingTool.logError(e.toString());
                  executor.shutdownNow();
                  throw new UncheckedIOException(e);
                } catch (NoSuchAlgorithmException e) {
                  LoggingTool.logError(e.toString());
                  e.printStackTrace();
                }
            }
    );
    }
  }

  public void processRequestAndResponse(SocketWrapper clientSocket) throws IOException, NoSuchAlgorithmException {
    Request request = new RequestProcessor().createRequest(clientSocket);
    Response response = Router.findResponse(request);
    new ResponseProcessor().sendResponse(clientSocket, new ResponseFormatter().formatResponse(response));
  }

  public void killServer() {
    inProgress = false;
  }

}
