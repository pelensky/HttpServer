package com.pelensky.httpserver.Router;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Routes.Route;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Router {

  public static Response findResponse(Request request) throws IOException, NoSuchAlgorithmException {
    for (Route selection : Routes.routes()) {
      if (selection.respondsTo(request)) {
        return selection.call(request);
      }
    }
    return null;
  }

  }
