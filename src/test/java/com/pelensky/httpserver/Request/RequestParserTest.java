package com.pelensky.httpserver.Request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {

    private RequestParser requestParser;
    private String requestString;

    @Before
    public void setUp() {
        requestString = "POST /form HTTP/1.1\n" +
                "User-Agent: HTTPTool/1.0\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 32\n" +
                "\n" +
                "name=dan&data=fatcat";
        requestParser = new RequestParser();
        requestParser.parseRequest(requestString);
    }

    @Test
    public void createsARequestObjectWithHeadersAndBody() {
        assertEquals("POST", requestParser.parseRequest(requestString).getMethod());
    }

    @Test
    public void handlesRequestWithNoHeadersOrBody() {
        final RequestParser shortRequestParser = new RequestParser();
        assertEquals("GET", shortRequestParser.parseRequest("GET / HTTP/1.1\n").getMethod());
    }

    @Test
    public void handlesRequestWithHeadersButNoBody() {
        RequestParser noBodyRequestParser = new RequestParser();
        assertEquals( "/form", noBodyRequestParser.parseRequest("POST /form HTTP/1.1\n" +
                "User-Agent: HTTPTool/1.0\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 32\n").getUri());
    }

}
