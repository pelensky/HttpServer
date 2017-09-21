package com.pelensky.httpserver.Utilities;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class BasicAuthorizationTest {
    BasicAuthorization basicAuthorization;

    @Before
    public void setUp() {
        basicAuthorization = new BasicAuthorization();
        basicAuthorization.add("secret", "dan", "asdf");
    }
    @Test
    public void acceptsARoute() {
        assertEquals(basicAuthorization.getList().get(0)[0], "secret");
        assertEquals(basicAuthorization.getList().get(0)[1], "dan");
        assertEquals(basicAuthorization.getList().get(0)[2], "asdf");
    }

    @Test
    public void checksIfAuthenticationIsRequired() throws UnsupportedEncodingException {
        Request request = new RequestParser().parseRequest("GET /secret HTTP/1.1");
       assertTrue(basicAuthorization.isAuthenticationRequired(request));
    }

    @Test
    public void checksAuthenticationNotRequired() throws UnsupportedEncodingException {
        Request request = new RequestParser().parseRequest("GET / HTTP/1.1");
        assertFalse(basicAuthorization.isAuthenticationRequired(request));
    }

    @Test
    public void authenticationMatches() throws UnsupportedEncodingException {
        Request request = new RequestParser().parseRequest("GET /secret HTTP/1.1\nAuthorization: Basic ZGFuOmFzZGY=\n");
        assertTrue(basicAuthorization.isAuthenticated(request));
    }

    @Test
    public void authenticationDoesntMatch() throws UnsupportedEncodingException {
        Request request = new RequestParser().parseRequest("GET /secret HTTP/1.1\nAuthorization: Basic ZGFuOmFzZGZhZHNmYWRz\n");
        assertFalse(basicAuthorization.isAuthenticated(request));
    }

}
