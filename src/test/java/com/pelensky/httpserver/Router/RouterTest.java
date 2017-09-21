package com.pelensky.httpserver.Router;

import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import com.pelensky.httpserver.Response.ResponseFormatter;
import com.pelensky.httpserver.Utilities.BasicAuthorization;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class RouterTest {

    @Test
    public void serverRespondsToGetRequestWith200() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET / HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nContent-Length: 428\n" +
                "\n" + "<!DOCTYPE html>\n<HTML>\n<HEAD>\n<TITLE>HttpServer</TITLE>\n</HEAD>\n<BODY>\n" + "<a href=\"/file1\">file1</a><br>\n<a href=\"/file2\">file2</a><br>\n<a href=\"/image.gif\">image.gif</a><br>\n<a href=\"/image.jpeg\">image.jpeg</a><br>\n<a href=\"/image.png\">image.png</a><br>\n<a href=\"/partial_content.txt\">partial_content.txt</a><br>\n<a href=\"/patch-content.txt\">patch-content.txt</a><br>\n<a href=\"/text-file.txt\">text-file.txt</a><br>\n" + "</BODY>\n</HTML>", getResponse(request));
    }

    @Test
    public void serverRespondsToUnknownGetRouteWith404() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /foobar HTTP/1.1\n");
        assertEquals("HTTP/1.1 404", getResponse(request));
    }

    @Test
    public void serverRespondsToUnknownHeadRouteWith404() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("HEAD /foobar HTTP/1.1\n");
        assertEquals("HTTP/1.1 404", getResponse(request));
    }

    @Test
    public void serverRespondsToUnknownPostRouteWith404() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("POST /foobar HTTP/1.1\n");
        assertEquals("HTTP/1.1 404", getResponse(request));
    }

    @Test
    public void serverRespondsToUnknownDeleteRouteWith404() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("DELETE /foobar HTTP/1.1\n");
        assertEquals("HTTP/1.1 404", getResponse(request));
    }

    @Test
    public void serverRespondsToUnknownPatchRouteWith404() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("PATCH /foobar HTTP/1.1\n");
        assertEquals("HTTP/1.1 404", getResponse(request));
    }

    @Test
    public void serverRespondsToUnknownPutRouteWith404() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("PUT /foobar HTTP/1.1\n");
        assertEquals("HTTP/1.1 404", getResponse(request));
    }

    @Test
    public void serverRespondsToPostRequestWith200() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("POST /form HTTP/1.1\nContent-Length: 348\n\nMy=Data");
        assertEquals("HTTP/1.1 200\nContent-Length: 7\n\nMy=Data", getResponse(request));
    }

    @Test
    public void serverRespondsToPostRequestWithBody() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat");
        assertEquals("HTTP/1.1 200\nContent-Length: 11\n\ndata=fatcat", getResponse(request));
    }

    @Test
    public void serverRespondsToPutRequestWith200() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("PUT /form HTTP/1.1\n");
        assertEquals("HTTP/1.1 200", getResponse(request));
    }

    @Test
    public void serverRespondsToDeleteRequestWith200() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("DELETE /form HTTP/1.1\n");
        assertEquals("HTTP/1.1 200", getResponse(request));
    }

    @Test
    public void serverRespondsToHeadRequestWith200() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("HEAD / HTTP/1.1\n");
        assertEquals("HTTP/1.1 200", getResponse(request));
    }

    @Test
    public void serverRespondsToOptionRequest() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("OPTIONS /method_options HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nAllow: GET,HEAD,POST,OPTIONS,PUT", getResponse(request));
    }

    @Test
    public void serverRespondsToRequestWithDifferentOptions() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("OPTIONS /method_options2 HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nAllow: GET,OPTIONS", getResponse(request));
    }

    @Test
    public void serverRespondsWith404ifTypeOfRequestIsWrong() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GETTING / HTTP/1.1\n");
        assertEquals("HTTP/1.1 405", getResponse(request));
    }

    @Test
    public void serverRespondsWith302WhenRedirected() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /redirect HTTP/1.1\n");
        assertEquals("HTTP/1.1 302\nLocation: /", getResponse(request));
    }

    @Test
    public void PostGetPutGetDeleteGet() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("POST /form HTTP/1.1\nContent-Length: 11\n\ndata=fatcat");
        assertEquals("HTTP/1.1 200\nContent-Length: 11\n\ndata=fatcat", getResponse(request));
        request = setUpRequest("GET /form HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nContent-Length: 11\n\ndata=fatcat", getResponse(request));
    }

    @Test
    public void File1GetsContentsOfFile() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /file1 HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nContent-Type: text/plain\nContent-Length: 14\n\nfile1 contents", getResponse(request));
    }

    @Test
    public void TextFileAllowsGetButNotPost() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /text-file.txt HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nContent-Type: text/plain\nContent-Length: 14\n\nfile1 contents", getResponse(request));
    }

    @Test
    public void PartialContentFirstFourBytes() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /partial_content.txt HTTP/1.1\nRange: bytes=0-4");
        assertEquals("HTTP/1.1 206\nContent-Range: bytes 0-4/76\nContent-Type: text/plain\nContent-Length: 5\n\nThis ", getResponse(request));
    }

    @Test
    public void PartialContentLastSixBytes() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /partial_content.txt HTTP/1.1\nRange: bytes=-6");
        assertEquals("HTTP/1.1 206\nContent-Range: bytes 71-76/76\nContent-Type: text/plain\nContent-Length: 6\n\n 206.\n", getResponse(request));
    }

    @Test
    public void PartialContentAllFromFour() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /partial_content.txt HTTP/1.1\nRange: bytes=4-");
        assertEquals("HTTP/1.1 206\nContent-Range: bytes 4-76/76\nContent-Type: text/plain\nContent-Length: 73\n\n is a file that contains text to read part of in order to fulfill a 206.\n", getResponse(request));
    }

    @Test
    public void ResponseToCoffeeWith418() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /coffee HTTP/1.1\n");
        assertEquals("HTTP/1.1 418\nContent-Length: 12\n\nI'm a teapot", getResponse(request));
    }

    @Test
    public void ResponseToTea() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /tea HTTP/1.1\n");
        assertEquals("HTTP/1.1 200", getResponse(request));
    }

    @Test
    public void BasicAuthRequired() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /logs HTTP/1.1\n");
        assertEquals("HTTP/1.1 401\nWWW-Authenticate: Basic realm=\"Authentication required\"", getResponse(request));
    }

    @Test
    public void decodesParameters() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nContent-Length: 96\n\nvariable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\nvariable_2 = stuff", getResponse(request));
    }


    @Test
    public void setCookieToChocolate() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /cookie?type=chocolate HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nSet-Cookie: type=chocolate\nContent-Length: 3\n\nEat", getResponse(request));
    }

    @Test
    public void displayCookieData() throws IOException, NoSuchAlgorithmException {
        Request request = setUpRequest("GET /eat_cookie HTTP/1.1\nCookie: type=chocolate\n");
        assertEquals("HTTP/1.1 200\nContent-Length: 14\n\nmmmm chocolate", getResponse(request));
    }

    @Test
    public void patchContent() throws IOException, NoSuchAlgorithmException {
        new FileProcessor().patchFile("patch-content.txt", "default content");
        Request request = setUpRequest("PATCH /patch-content.txt HTTP/1.1\nIf-Match: dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec\nContent-Length: 15\n\npatched content");
        assertEquals("HTTP/1.1 204\nETag: 5c36acad75b78b82be6d9cbbd6143ab7e0cc04b0", getResponse(request));
        Request nextRequest = setUpRequest("GET /patch-content.txt HTTP/1.1\n");
        assertEquals("HTTP/1.1 200\nContent-Type: text/plain\nContent-Length: 15\n\npatched content", getResponse(nextRequest));
        new FileProcessor().patchFile("patch-content.txt", "default content");
    }

    private Request setUpRequest(String request) throws UnsupportedEncodingException {
        return new RequestParser().parseRequest(request);
    }

    private String getResponse(Request request) throws IOException, NoSuchAlgorithmException {
        BasicAuthorization basicAuthorization = new BasicAuthorization();
        basicAuthorization.add("logs", "admin", "hunter2");
        return new String(new ResponseFormatter().formatResponse(Router.findResponse(basicAuthorization, request)));
    }

}
