package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Routes.Route;
import com.pelensky.httpserver.Routes.Routes;

public class ResponseFormatter {

  public static String getResponse(Request request) {
    String method = request.getMethod();
    String uri = request.getUri();
    for (Route selection : Routes.routes()) {
      if (selection.route().equals(uri)) {
        return selection.call(method);
      }
    }
    return Status.codes().get(404);
  }
}
