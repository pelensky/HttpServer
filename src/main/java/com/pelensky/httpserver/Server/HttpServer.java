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
import com.pelensky.httpserver.Utilities.BasicAuthorization;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

  private final ServerSocketWrapper serverSocket;
  private final BasicAuthorization basicAuthorization;
  private final ExecutorService executor = Executors.newFixedThreadPool(10);

  private boolean inProgress = true;

  public HttpServer(ServerSocketWrapper serverSocket, BasicAuthorization basicAuthorization) {
    this.serverSocket = serverSocket;
    this.basicAuthorization = basicAuthorization;
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
    Response response = Router.findResponse(basicAuthorization, request);
    new ResponseProcessor().sendResponse(clientSocket, new ResponseFormatter().formatResponse(response));
  }

  public void killServer() {
    inProgress = false;
  }

}
