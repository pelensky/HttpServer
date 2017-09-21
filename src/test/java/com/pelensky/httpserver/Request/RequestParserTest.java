package com.pelensky.httpserver.Request;

import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {

    private RequestParser requestParser;
    private String requestString;

    @Before
    public void setUp() throws UnsupportedEncodingException {
        requestString = "POST /form HTTP/1.1\n" +
                "User-Agent: HTTPTool/1.0\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 32\n" +
                "\n" +
                "name=dan&data=fatcat";
        requestParser = new RequestParser();
    }

    @Test
    public void createsARequestObjectWithHeadersAndBody() throws UnsupportedEncodingException {
        assertEquals("POST", requestParser.parseRequest(requestString).getMethod());
    }

    @Test
    public void handlesRequestWithNoHeadersOrBody() throws UnsupportedEncodingException {
        assertEquals("GET", requestParser.parseRequest("GET / HTTP/1.1\n").getMethod());
    }

    @Test
    public void handlesRequestWithHeadersButNoBody() throws UnsupportedEncodingException {
        assertEquals( "form", requestParser.parseRequest("POST /form HTTP/1.1\n" +
                "User-Agent: HTTPTool/1.0\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 32\n").getUri());
    }

    @Test
    public void handlesRequestWithOneSetOfParameters() throws UnsupportedEncodingException {
        assertEquals("cool", requestParser.parseRequest("GET /hello?dan=cool HTTP/1.1\n").getParameters().get("dan"));
    }

    @Test
    public void handlesRequestWithMultipleSetsOfParameters() throws UnsupportedEncodingException {
        assertEquals("4", requestParser.parseRequest("GET /hello?a=1&b=2&c=3&d=4 HTTP/1.1\n").getParameters().get("d"));
    }

    @Test
    public void handlesEncodedParams() throws UnsupportedEncodingException {
        assertEquals("Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?", (requestParser.parseRequest("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F HTTP/1.1\n").getParameters().get("variable_1")));
    }

    @Test
    public void handlesMultipleParamsWhenEncoded() throws UnsupportedEncodingException {
        assertEquals("stuff", (requestParser.parseRequest("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\n").getParameters().get("variable_2")));
    }

    @Test
    public void identifiesURI() throws UnsupportedEncodingException {
        assertEquals("parameters", (requestParser.parseRequest("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\n").getUri()));
    }

    @Test
    public void identifiesHttpVersion() throws UnsupportedEncodingException {
        assertEquals("HTTP/1.1", (requestParser.parseRequest("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\n").getHttpVersion()));
    }
}
