package com.pelensky.httpserver.Router;

import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Routes.File;
import com.pelensky.httpserver.Routes.Route;
import com.pelensky.httpserver.Routes.Routes;

import java.io.IOException;
import java.util.regex.Pattern;

public class Router {

  public static Response findResponse(Request request) throws IOException {
    if (publicDirectoryContainsFile(request)) return new File().call(request);
    for (Route selection : Routes.routes()) {
      if (existingRoute(selection, request)) {
        return selection.call(request);
      }
    }
    return new Response(404);
  }

  private static Boolean existingRoute (Route route, Request request) {
    return (route.route().equals(request.getUri()));
  }

  private static Boolean publicDirectoryContainsFile(Request request) {
    String fileNameAndType = (request.getFileType() != null) ? request.getUri() + "." + request.getFileType() : request.getUri();
    return new FileProcessor().directoryContainsFile(fileNameAndType) && !request.getUri().isEmpty();
  }

}
