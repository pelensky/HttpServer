package com.pelensky.httpserver.Request;

import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RequestTest {

    private Request request;

    @Before
    public void setUp() {
        request = new Request("POST", "form", null, "HTTP/1.1", setUpHeaders(), "name=dan&data=fatcat", null);
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
        request = new Request("GET", "image", "jpeg", "HTTP/1.1", null, null, null);
        assertEquals("jpeg", request.getFileType());
    }

    @Test
    public void findsETagIfApplicable() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET /test HTTP/1.1\nIf-Match: hello");
        assertEquals("hello", request.findETag());
    }

    @Test
    public void returnFileName() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET /file.jpg HTTP/1.1");
        assertEquals("file.jpg", request.findFileName());
    }

    @Test
    public void checksIfURIIsAFile() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET /file.jpg HTTP/1.1");
        assertTrue(request.isAFile());
    }

    @Test
    public void checksIfURIIsNotAFile() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET / HTTP/1.1");
        assertFalse(request.isAFile());
    }

    @Test
    public void checksForParameters() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET /cookie?type=chocolate HTTP/1.1\\n");
        assertTrue(request.hasParameters());
    }

    @Test
    public void checksForNoParameters() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET / HTTP/1.1");
        assertFalse(request.hasParameters());
    }

    @Test
    public void checkForHeaders() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET /test HTTP/1.1\nIf-Match: hello");
        assertTrue(request.hasHeader());
    }

    @Test
    public void checksForNoHeaders() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET / HTTP/1.1\n");
        assertFalse(request.hasHeader());
    }

    @Test
    public void getsASingleCookie() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET /eat_cookie HTTP/1.1\nCookie: yummy_cookie=choco\n");
        assertEquals("choco", request.getCookie().get("yummy_cookie"));
    }

    @Test
    public void getsMultipleCookies() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET /eat_cookie HTTP/1.1\nCookie: yummy_cookie=choco; tasty_cookie=strawberry\n");
        assertEquals("choco", request.getCookie().get("yummy_cookie"));
        assertEquals("strawberry", request.getCookie().get("tasty_cookie"));
    }

    @Test
    public void checkForCookies() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET /eat_cookie HTTP/1.1\nCookie: yummy_cookie=choco\n");
        assertTrue(request.hasCookies());
    }

    @Test
    public void checksForNoCookies() throws UnsupportedEncodingException {
        request = new RequestParser().parseRequest("GET / HTTP/1.1\n");
        assertFalse(request.hasCookies());
    }

    private Map<String, String> setUpHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Length", "32");
        headers.put("User-Agent", "HTTPTool/1.0");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

}
