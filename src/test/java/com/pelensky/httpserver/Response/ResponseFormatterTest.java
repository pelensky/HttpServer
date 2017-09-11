package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseFormatterTest {

    @Test
    public void serverRespondsToGetRequestWith200() {
        Request request = new RequestParser("GET / HTTP/1.1\n").parseRequest();
        assertEquals("HTTP/1.1 200 OK", ResponseFormatter.getResponse(request));
    }

    @Test
    public void serverRespondsToUnknownGetRouteWith404() {
        Request request = new RequestParser("GET /foobar HTTP/1.1\n").parseRequest();
        assertEquals("HTTP/1.1 404 Not Found", ResponseFormatter.getResponse(request));
    }

    @Test
    public void serverRespondsToPostRequestWith200() {
        Request request = new RequestParser("POST /form HTTP/1.1\nContent-Length: 348\n\n\"My\"=\"Data\"\n").parseRequest();
        assertEquals("HTTP/1.1 200 OK", ResponseFormatter.getResponse(request));
    }

    @Test
    public void serverRespondsToPutRequestWith200() {
        Request request = new RequestParser("PUT /form HTTP/1.1\n").parseRequest();
        assertEquals("HTTP/1.1 200 OK", ResponseFormatter.getResponse(request));
    }

    @Test
    public void serverRespondsToHeadRequestWith200() {
        Request request = new RequestParser("HEAD / HTTP/1.1\n").parseRequest();
        assertEquals("HTTP/1.1 200 OK", ResponseFormatter.getResponse(request));
    }

    @Test
    public void serverRespondsToOptionRequest() {
        Request request = new RequestParser("OPTIONS /method_options HTTP/1.1\n").parseRequest();
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT", ResponseFormatter.getResponse(request));
    }

    @Test
    public void serverRespondsToRequestWithDifferentOptions() {
        Request request = new RequestParser("OPTIONS /method_options2 HTTP/1.1\n").parseRequest();
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,OPTIONS", ResponseFormatter.getResponse(request));
    }

    @Test
    public void serverRespondsWith404ifTypeOfRequestIsWrong() {
        Request request = new RequestParser("GETTING / HTTP/1.1\n").parseRequest();
        assertEquals("HTTP/1.1 404 Not Found", ResponseFormatter.getResponse(request));
    }

    @Test
    public void serverRespondsWith302WhenRedirected() {
        Request request = new RequestParser("GET /redirect HTTP/1.1\n").parseRequest();
        assertEquals("HTTP/1.1 302 Found\nLocation: /", ResponseFormatter.getResponse(request));
    }
}
