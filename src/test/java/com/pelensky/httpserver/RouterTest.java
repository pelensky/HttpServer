package com.pelensky.httpserver;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import com.pelensky.httpserver.Response.ResponseFormatter;
import com.pelensky.httpserver.Router.Router;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RouterTest {

    @Test
    public void serverRespondsToGetRequestWith200() throws IOException {
        Request request = setUpRequest("GET / HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nContent-Length: 428\n" +
                "\n" + "<!DOCTYPE html>\n<HTML>\n<HEAD>\n<TITLE>HttpServer</TITLE>\n</HEAD>\n<BODY>\n" + "<a href=\"/file1\">file1</a><br>\n<a href=\"/file2\">file2</a><br>\n<a href=\"/image.gif\">image.gif</a><br>\n<a href=\"/image.jpeg\">image.jpeg</a><br>\n<a href=\"/image.png\">image.png</a><br>\n<a href=\"/partial_content.txt\">partial_content.txt</a><br>\n<a href=\"/patch-content.txt\">patch-content.txt</a><br>\n<a href=\"/text-file.txt\">text-file.txt</a><br>\n" + "</BODY>\n</HTML>", getResponse(request));
    }

    @Test
    public void serverRespondsToUnknownGetRouteWith404() throws IOException {
        Request request = setUpRequest("GET /foobar HTTP/1.1\n");
        assertEquals("HTTP/1.1 404", getResponse(request));
    }

    @Test
    public void serverRespondsToPostRequestWith200() throws IOException {
        Request request = setUpRequest("POST /form HTTP/1.1\nContent-Length: 348\n\nMy=Data");
        assertEquals("HTTP/1.1 200\nContent-Length: 7\n\nMy=Data", getResponse(request));
    }

    @Test
    public void serverRespondsToPostRequestWithBody() throws IOException {
        Request request = setUpRequest("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat");
        assertEquals("HTTP/1.1 200\nContent-Length: 11\n\ndata=fatcat", getResponse(request));
    }

    @Test
    public void serverRespondsToPutRequestWith200() throws IOException {
        Request request = setUpRequest("PUT /form HTTP/1.1\n");
        assertEquals("HTTP/1.1 200", getResponse(request));
    }

    @Test
    public void serverRespondsToHeadRequestWith200() throws IOException {
        Request request = setUpRequest("HEAD / HTTP/1.1\n");
        assertEquals("HTTP/1.1 200", getResponse(request));
    }

    @Test
    public void serverRespondsToOptionRequest() throws IOException {
        Request request = setUpRequest("OPTIONS /method_options HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nAllow: GET,HEAD,POST,OPTIONS,PUT", getResponse(request));
    }

    @Test
    public void serverRespondsToRequestWithDifferentOptions() throws IOException {
        Request request = setUpRequest("OPTIONS /method_options2 HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nAllow: GET,OPTIONS", getResponse(request));
    }

    @Test
    public void serverRespondsWith404ifTypeOfRequestIsWrong() throws IOException {
        Request request = setUpRequest("GETTING / HTTP/1.1\n");
        assertEquals("HTTP/1.1 405", getResponse(request));
    }

    @Test
    public void serverRespondsWith302WhenRedirected() throws IOException {
        Request request = setUpRequest("GET /redirect HTTP/1.1\n");
        assertEquals("HTTP/1.1 302\nLocation: /", getResponse(request));
    }

    @Test
    public void PostGetPutGetDeleteGet() throws IOException {
        Request request = setUpRequest("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat");
        assertEquals("HTTP/1.1 200\nContent-Length: 11\n\ndata=fatcat", getResponse(request));
        request = setUpRequest("GET /form HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nContent-Length: 11\n\ndata=fatcat", getResponse(request));
    }

    @Test
    public void File1GetsContentsOfFile() throws IOException {
        Request request = setUpRequest("GET /file1 HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nContent-Type: text/plain\nContent-Length: 14\n\nfile1 contents", getResponse(request));
    }

    @Test
    public void TextFileAllowsGetButNotPost() throws IOException {
        Request request = setUpRequest("GET /text-file.txt HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nContent-Type: text/plain\nContent-Length: 14\n\nfile1 contents", getResponse(request));
    }

    @Test
    public void PartialContentFirstFourBytes() throws IOException {
        Request request = setUpRequest("GET /partial_content.txt HTTP/1.1\nRange: bytes=0-4");
        assertEquals("HTTP/1.1 206\nContent-Range: bytes 0-4/76\nContent-Type: text/plain\nContent-Length: 5\n\nThis ", getResponse(request));
    }

    @Test
    public void PartialContentLastSixBytes() throws IOException {
        Request request = setUpRequest("GET /partial_content.txt HTTP/1.1\nRange: bytes=-6");
        assertEquals("HTTP/1.1 206\nContent-Range: bytes 71-76/76\nContent-Type: text/plain\nContent-Length: 6\n\n 206.\n", getResponse(request));
    }

    @Test
    public void PartialContentAllFromFour() throws IOException {
        Request request = setUpRequest("GET /partial_content.txt HTTP/1.1\nRange: bytes=4-");
        assertEquals("HTTP/1.1 206\nContent-Range: bytes 4-76/76\nContent-Type: text/plain\nContent-Length: 73\n\n is a file that contains text to read part of in order to fulfill a 206.\n", getResponse(request));
    }

    @Test
    public void ResponseToCoffeeWith418() throws IOException {
        Request request = setUpRequest("GET /coffee HTTP/1.1\n");
        assertEquals("HTTP/1.1 418\nContent-Length: 12\n\nI'm a teapot", getResponse(request));
    }

    @Test
    public void ResponseToTea() throws IOException {
        Request request = setUpRequest("GET /tea HTTP/1.1\n");
        assertEquals("HTTP/1.1 200", getResponse(request));
    }

    @Test
    public void BasicAuthRequired() throws IOException {
        Request request = setUpRequest("GET /logs HTTP/1.1\n");
        assertEquals("HTTP/1.1 401\nWWW-Authenticate: Basic realm=\"Authentication required\"", getResponse(request));
    }

    private Request setUpRequest(String request) {
        return new RequestParser().parseRequest(request);
    }

    private String getResponse(Request request) throws IOException {
        return new String(new ResponseFormatter().formatResponse(Router.findResponse(request)));
    }



}
