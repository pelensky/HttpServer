package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Routes.Route;
import com.pelensky.httpserver.Routes.Routes;

public class ResponseFinder {

  public static Response getResponse(Request request) {
    String method = request.getMethod();
    String uri = request.getUri();
    for (Route selection : Routes.routes()) {
      if (selection.route().equals(uri)) {
        return selection.call(method);
      }
    }
    return new Response(404);
  }
}
