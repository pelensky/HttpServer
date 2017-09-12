package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ResponseFinderTest {

    @Test
    public void serverRespondsToGetRequestWith200() throws IOException {
        Request request = new RequestParser("GET / HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\n", response);
    }

    @Test
    public void serverRespondsToUnknownGetRouteWith404() throws IOException {
        Request request = new RequestParser("GET /foobar HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 404 Not Found\n", response);
    }

    @Test
    public void serverRespondsToPostRequestWith200() throws IOException {
        Request request = new RequestParser("POST /form HTTP/1.1\nContent-Length: 348\n\nMy=Data").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 8\n\nMy=Data\n", response);
    }

    @Test
    public void serverRespondsToPostRequestWithBody() throws IOException {
        Request request = new RequestParser("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 12\n\ndata=fatcat\n", response);
    }

    @Test
    public void serverRespondsToPutRequestWith200() throws IOException {
        Request request = new RequestParser("PUT /form HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\n", response);
    }

    @Test
    public void serverRespondsToHeadRequestWith200() throws IOException {
        Request request = new RequestParser("HEAD / HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\n", response);
    }

    @Test
    public void serverRespondsToOptionRequest() throws IOException {
        Request request = new RequestParser("OPTIONS /method_options HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT\n", response);
    }

    @Test
    public void serverRespondsToRequestWithDifferentOptions() throws IOException {
        Request request = new RequestParser("OPTIONS /method_options2 HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,OPTIONS\n", response);
    }

    @Test
    public void serverRespondsWith404ifTypeOfRequestIsWrong() throws IOException {
        Request request = new RequestParser("GETTING / HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 405 Method Not Allowed\n", response);
    }

    @Test
    public void serverRespondsWith302WhenRedirected() throws IOException {
        Request request = new RequestParser("GET /redirect HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 302 Found\nLocation: /\n", response);
    }

    @Test
    public void PostGetPutGetDeleteGet() throws IOException {
        Request request = new RequestParser("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 12\n\ndata=fatcat\n", response);
        Request getRequest = new RequestParser("GET /form HTTP/1.1\n").parseRequest();
        String getResponse = new ResponseFormatter().format(ResponseFinder.getResponse(getRequest));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 12\n\ndata=fatcat\n", getResponse);
    }

    @Test
    public void File1GetsContentsOfFile() throws IOException {
        Request request = new RequestParser("GET /file1 HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 14\n\nfile1 contents\n", response);
    }

    @Test
    public void TextFileAllowsGetButNotPost() throws IOException {
        Request request = new RequestParser("GET /text-file.txt HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 14\n\nfile1 contents\n", response);
    }
}
