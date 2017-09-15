package com.pelensky.httpserver.Router;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Routes.Route;
import com.pelensky.httpserver.Routes.Routes;

import java.io.IOException;
import java.util.regex.Pattern;

public class Router {

  public static Response findResponse(Request request) throws IOException {
    for (Route selection : Routes.routes()) {
      if ((selection.route()).equals(request.getUri())) {
        return selection.call(request);
      }
    }
    return new Response(404);
  }

}
