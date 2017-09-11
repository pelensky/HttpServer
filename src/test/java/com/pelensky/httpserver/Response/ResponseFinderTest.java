package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseFinderTest {

    @Test
    public void serverRespondsToGetRequestWith200() {
        Request request = new RequestParser("GET / HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\n", response);
    }

    @Test
    public void serverRespondsToUnknownGetRouteWith404() {
        Request request = new RequestParser("GET /foobar HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 404 Not Found\n", response);
    }

    @Test
    public void serverRespondsToPostRequestWith200() {
        Request request = new RequestParser("POST /form HTTP/1.1\nContent-Length: 348\n\n\"My\"=\"Data\"\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\n", response);
    }

    @Test
    public void serverRespondsToPutRequestWith200() {
        Request request = new RequestParser("PUT /form HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\n", response);
    }

    @Test
    public void serverRespondsToHeadRequestWith200() {
        Request request = new RequestParser("HEAD / HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\n", response);
    }

    @Test
    public void serverRespondsToOptionRequest() {
        Request request = new RequestParser("OPTIONS /method_options HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT\n", response);
    }

    @Test
    public void serverRespondsToRequestWithDifferentOptions() {
        Request request = new RequestParser("OPTIONS /method_options2 HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,OPTIONS\n", response);
    }

    @Test
    public void serverRespondsWith404ifTypeOfRequestIsWrong() {
        Request request = new RequestParser("GETTING / HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 404 Not Found\n", response);
    }

    @Test
    public void serverRespondsWith302WhenRedirected() {
        Request request = new RequestParser("GET /redirect HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 302 Found\nLocation: /\n", response);
    }
}
