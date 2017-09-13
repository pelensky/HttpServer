package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ResponseFinderTest {

    @Test
    public void serverRespondsToGetRequestWith200() throws IOException {
        Request request = setUpRequest("GET / HTTP/1.1\n");
        assertEquals("HTTP/1.1 200 OK", getResponse(request));
    }

    @Test
    public void serverRespondsToUnknownGetRouteWith404() throws IOException {
        Request request = setUpRequest("GET /foobar HTTP/1.1\n");
        assertEquals("HTTP/1.1 404 Not Found", getResponse(request));
    }

    @Test
    public void serverRespondsToPostRequestWith200() throws IOException {
        Request request = setUpRequest("POST /form HTTP/1.1\nContent-Length: 348\n\nMy=Data");
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 7\n\nMy=Data", getResponse(request));
    }

    @Test
    public void serverRespondsToPostRequestWithBody() throws IOException {
        Request request = setUpRequest("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat");
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 11\n\ndata=fatcat", getResponse(request));
    }

    @Test
    public void serverRespondsToPutRequestWith200() throws IOException {
        Request request = setUpRequest("PUT /form HTTP/1.1\n");
        assertEquals("HTTP/1.1 200 OK", getResponse(request));
    }

    @Test
    public void serverRespondsToHeadRequestWith200() throws IOException {
        Request request = setUpRequest("HEAD / HTTP/1.1\n");
        assertEquals("HTTP/1.1 200 OK", getResponse(request));
    }

    @Test
    public void serverRespondsToOptionRequest() throws IOException {
        Request request = setUpRequest("OPTIONS /method_options HTTP/1.1\n");
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT", getResponse(request));
    }

    @Test
    public void serverRespondsToRequestWithDifferentOptions() throws IOException {
        Request request = setUpRequest("OPTIONS /method_options2 HTTP/1.1\n");
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,OPTIONS", getResponse(request));
    }

    @Test
    public void serverRespondsWith404ifTypeOfRequestIsWrong() throws IOException {
        Request request = setUpRequest("GETTING / HTTP/1.1\n");
        assertEquals("HTTP/1.1 405 Method Not Allowed", getResponse(request));
    }

    @Test
    public void serverRespondsWith302WhenRedirected() throws IOException {
        Request request = setUpRequest("GET /redirect HTTP/1.1\n");
        assertEquals("HTTP/1.1 302 Found\nLocation: /", getResponse(request));
    }

    @Test
    public void PostGetPutGetDeleteGet() throws IOException {
        Request request = setUpRequest("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat");
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 11\n\ndata=fatcat", getResponse(request));
        request = setUpRequest("GET /form HTTP/1.1\n");
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 11\n\ndata=fatcat", getResponse(request));
    }

    @Test
    public void File1GetsContentsOfFile() throws IOException {
        Request request = setUpRequest("GET /file1 HTTP/1.1\n");
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 14\n\nfile1 contents", getResponse(request));
    }

    @Test
    public void TextFileAllowsGetButNotPost() throws IOException {
        Request request = setUpRequest("GET /text-file.txt HTTP/1.1\n");
        assertEquals("HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: 14\n\nfile1 contents", getResponse(request));
    }

    @Test
    public void PartialContentFirstFourBytes() throws IOException {
        Request request = setUpRequest("GET /partial_content.txt HTTP/1.1\nRange: bytes=0-4");
        assertEquals("HTTP/1.1 206 Partial Content\nContent-Range: bytes 0-4/76\nContent-Type: text/plain\nContent-Length: 5\n\nThis ", getResponse(request));
    }

    @Test
    public void PartialContentLastSixBytes() throws IOException {
        Request request = setUpRequest("GET /partial_content.txt HTTP/1.1\nRange: bytes=-6");
        assertEquals("HTTP/1.1 206 Partial Content\nContent-Range: bytes 71-76/76\nContent-Type: text/plain\nContent-Length: 6\n\n 206.\n", getResponse(request));
    }

    @Test
    public void PartialContentAllFromFour() throws IOException {
        Request request = setUpRequest("GET /partial_content.txt HTTP/1.1\nRange: bytes=4-");
        assertEquals("HTTP/1.1 206 Partial Content\nContent-Range: bytes 4-76/76\nContent-Type: text/plain\nContent-Length: 73\n\n is a file that contains text to read part of in order to fulfill a 206.\n", getResponse(request));
    }

    @Test
    public void FindsImages() throws IOException {
        Request request = setUpRequest("GET /image.jpeg HTTP/1.1\n");
        assertEquals("HTTP/1.1 200 OK\nContent-Type: image/jpeg", getResponse(request));
    }

    @Test
    public void ResponseToCoffeeWith418() throws IOException {
        Request request = setUpRequest("GET /coffee HTTP/1.1\n");
        assertEquals("HTTP/1.1 418 I'm a teapot\nContent-Length: 12\n\nI'm a teapot", getResponse(request));
    }

    @Test
    public void ResponseToTea() throws IOException {
        Request request = setUpRequest("GET /tea HTTP/1.1\n");
        assertEquals("HTTP/1.1 200 OK", getResponse(request));
    }

    private Request setUpRequest(String request) {
        return new RequestParser().parseRequest(request);
    }

    private String getResponse(Request request) throws IOException {
        return new ResponseFormatter().format(ResponseFinder.findResponse(request));
    }



}
