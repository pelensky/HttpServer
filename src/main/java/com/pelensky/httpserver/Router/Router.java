package com.pelensky.httpserver.Router;

import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;
import com.pelensky.httpserver.Routes.File;
import com.pelensky.httpserver.Routes.Route;
import com.pelensky.httpserver.Routes.Routes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Router {

  public static Response findResponse(Request request) throws IOException, NoSuchAlgorithmException {
    if (publicDirectoryContainsFile(request)) return new File().call(request);
    for (Route selection : Routes.routes()) {
      if (existingRoute(selection, request)) {
        return selection.call(request);
      }
    }
    return new Response(Status.NOT_FOUND.code());
  }

  private static Boolean existingRoute (Route route, Request request) {
    return (route.route().equals(request.getUri()));
  }

  private static Boolean publicDirectoryContainsFile(Request request) {
    String fileNameAndType = (request.getFileType() != null) ? request.getUri() + "." + request.getFileType() : request.getUri();
    return new FileProcessor().doesFileExistInDirectory(fileNameAndType) && !request.getUri().isEmpty();
  }

}
