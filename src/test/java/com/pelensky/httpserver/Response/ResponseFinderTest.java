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
        assertEquals("HTTP/1.1 200 OK", response);
    }

    @Test
    public void serverRespondsToUnknownGetRouteWith404() throws IOException {
        Request request = new RequestParser("GET /foobar HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 404 Not Found", response);
    }

    @Test
    public void serverRespondsToPostRequestWith200() throws IOException {
        Request request = new RequestParser("POST /form HTTP/1.1\nContent-Length: 348\n\nMy=Data").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 7\n\nMy=Data", response);
    }

    @Test
    public void serverRespondsToPostRequestWithBody() throws IOException {
        Request request = new RequestParser("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 11\n\ndata=fatcat", response);
    }

    @Test
    public void serverRespondsToPutRequestWith200() throws IOException {
        Request request = new RequestParser("PUT /form HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK", response);
    }

    @Test
    public void serverRespondsToHeadRequestWith200() throws IOException {
        Request request = new RequestParser("HEAD / HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK", response);
    }

    @Test
    public void serverRespondsToOptionRequest() throws IOException {
        Request request = new RequestParser("OPTIONS /method_options HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT", response);
    }

    @Test
    public void serverRespondsToRequestWithDifferentOptions() throws IOException {
        Request request = new RequestParser("OPTIONS /method_options2 HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,OPTIONS", response);
    }

    @Test
    public void serverRespondsWith404ifTypeOfRequestIsWrong() throws IOException {
        Request request = new RequestParser("GETTING / HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 405 Method Not Allowed", response);
    }

    @Test
    public void serverRespondsWith302WhenRedirected() throws IOException {
        Request request = new RequestParser("GET /redirect HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 302 Found\nLocation: /", response);
    }

    @Test
    public void PostGetPutGetDeleteGet() throws IOException {
        Request request = new RequestParser("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 11\n\ndata=fatcat", response);
        Request getRequest = new RequestParser("GET /form HTTP/1.1\n").parseRequest();
        String getResponse = new ResponseFormatter().format(ResponseFinder.getResponse(getRequest));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 11\n\ndata=fatcat", getResponse);
    }

    @Test
    public void File1GetsContentsOfFile() throws IOException {
        Request request = new RequestParser("GET /file1 HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 14\n\nfile1 contents", response);
    }

    @Test
    public void TextFileAllowsGetButNotPost() throws IOException {
        Request request = new RequestParser("GET /text-file.txt HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 14\n\nfile1 contents", response);
    }

    @Test
    public void PartialContentFirstFourBytes() throws IOException {
        Request request = new RequestParser("GET /partial_content.txt HTTP/1.1\nRange: bytes=0-4").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 206 Partial Content\nContent-Range: bytes 0-4/76\nContent-Type: text/plain\nContent-Length: 5\n\nThis ", response);
    }

    @Test
    public void PartialContentLastSixBytes() throws IOException {
        Request request = new RequestParser("GET /partial_content.txt HTTP/1.1\nRange: bytes=-6").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 206 Partial Content\nContent-Range: bytes 71-76/76\nContent-Type: text/plain\nContent-Length: 6\n\n 206.\n", response);
    }

    @Test
    public void PartialContentAllFromFour() throws IOException {
        Request request = new RequestParser("GET /partial_content.txt HTTP/1.1\nRange: bytes=4-").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 206 Partial Content\nContent-Range: bytes 4-76/76\nContent-Type: text/plain\nContent-Length: 73\n\n is a file that contains text to read part of in order to fulfill a 206.\n", response);
    }

    @Test
    public void FindsJpegImage() throws IOException {
        Request request = new RequestParser("GET /image.jpeg HTTP/1.1\n").parseRequest();
        String response = new ResponseFormatter().format(ResponseFinder.getResponse(request));
        assertEquals("HTTP/1.1 200 OK\nContent-Type: image/jpeg", response);
    }

}
