package com.pelensky.httpserver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {

    private RequestParser requestParser;
    private RequestParser shortRequestParser;

    @Before
    public void setUp() {
        requestParser = new RequestParser("POST /form HTTP/1.1\n" +
                "User-Agent: HTTPTool/1.0\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 32\n" +
                "\n" +
                "name=dan&data=fatcat");
        requestParser.parseRequest();
    }

    @Test
    public void getsRequestLine() {
        assertEquals("POST /form HTTP/1.1", requestParser.getRequestLine());
    }

    @Test
    public void getsRequestType() {
        assertEquals("POST", requestParser.getMethod());
    }

    @Test
    public void getsRequestRoute() {
        assertEquals("/form", requestParser.getUri());
    }

    @Test
    public void getsRequestHttpVersion() {
        assertEquals("HTTP/1.1", requestParser.getHttpVersion());
    }

    @Test
    public void getHeaders() {
        assertEquals("32", requestParser.getHeaders().get("Content-Length"));
        assertEquals("HTTPTool/1.0", requestParser.getHeaders().get("User-Agent"));
        assertEquals("application/x-www-form-urlencoded", requestParser.getHeaders().get("Content-Type"));
    }

    @Test
    public void getBody() {
        assertEquals("dan", requestParser.getBody().get("name"));
        assertEquals("fatcat", requestParser.getBody().get("data"));
    }

    @Test
    public void createsARequestObjectWithHeadersAndBody() {
        assertEquals("POST", requestParser.parseRequest().getMethod());
    }

    @Test
    public void handlesRequestWithNoHeadersOrBody() {
        shortRequestParser = new RequestParser("GET / HTTP/1.1\n");
        shortRequestParser.parseRequest();
        assertEquals("GET", shortRequestParser.parseRequest().getMethod());
    }

    @Test
    public void handlesRequestWithHeadersButNoBody() {
        RequestParser noBodyRequestParser = new RequestParser("POST /form HTTP/1.1\n" +
                "User-Agent: HTTPTool/1.0\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 32\n");
        assertEquals( "/form", noBodyRequestParser.parseRequest().getUri());
    }


}
