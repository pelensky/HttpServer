package com.pelensky.httpserver;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RequestTest {

    private Request request;

    @Before
    public void setUp() {
        request = new Request("POST", "/form", "HTTP/1.1", setUpHeaders(), setUpBody());
    }

    @Test
    public void getMethod() {
        assertEquals("POST", request.getMethod());
    }

    @Test
    public void getURI() {
        assertEquals("/form", request.getUri());
    }

    @Test
    public void getHttpVersion() {
        assertEquals("HTTP/1.1", request.getHttpVersion());
    }

    @Test
    public void getHeaders() {
        assertEquals("32", request.getHeaders().get("Content-Length"));
        assertEquals("HTTPTool/1.0",request.getHeaders().get("User-Agent"));
        assertEquals("application/x-www-form-urlencoded", request.getHeaders().get("Content-Type"));
    }

    @Test
    public void getBody() {
        assertEquals("dan", request.getBody().get("name"));
        assertEquals("fatcat", request.getBody().get("data"));
    }

    @Test
    public void handlesRequestsWithoutHeadersOrABody() {
        request = new Request("GET", "/", "HTTP/1.1");
        assertEquals("GET", request.getMethod());
    }

    @Test
    public void handlesRequestsWithHeadersButWithoutBody() {
        request = new Request("GET", "/", "HTTP/1.1", setUpHeaders());
        assertEquals("/", request.getUri());
    }

    private Map<String, String> setUpHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Length", "32");
        headers.put("User-Agent", "HTTPTool/1.0");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

    private Map<String, String> setUpBody() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "dan");
        body.put("data", "fatcat");
        return body;
    }
}
