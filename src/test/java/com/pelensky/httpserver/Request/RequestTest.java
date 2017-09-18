package com.pelensky.httpserver.Request;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RequestTest {

    private Request request;

    @Before
    public void setUp() {
        request = new Request("POST", "form", null, "HTTP/1.1", setUpHeaders(), "name=dan&data=fatcat");
    }

    @Test
    public void getMethod() {
        assertEquals("POST", request.getMethod());
    }

    @Test
    public void getURI() {
        assertEquals("form", request.getUri());
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
        assertEquals("name=dan&data=fatcat", request.getBody());
    }

    @Test
    public void findsFileType() {
        request = new Request("GET", "image", "jpeg", "HTTP/1.1", null, null);
        assertEquals("jpeg", request.getFileType());
    }

    private Map<String, String> setUpHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Length", "32");
        headers.put("User-Agent", "HTTPTool/1.0");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

}
