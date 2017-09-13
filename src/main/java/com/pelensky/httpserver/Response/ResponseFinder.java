package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Routes.Route;
import com.pelensky.httpserver.Routes.Routes;

import java.io.IOException;

public class ResponseFinder {

  public static Response findResponse(Request request) throws IOException {
    String uri = request.getUri();
    for (Route selection : Routes.routes()) {
      if (("/" + selection.route()).equals(uri)) {
        return selection.call(request);
      }
    }
    return new Response(404);
  }
}
