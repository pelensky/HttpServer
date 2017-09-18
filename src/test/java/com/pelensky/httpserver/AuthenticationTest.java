package com.pelensky.httpserver;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import org.junit.Before;
import org.junit.Test;

import java.util.Base64;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class AuthenticationTest {

    private Authentication authentication;

    @Before
    public void setUp() {
        authentication = new Authentication("username", "password");
    }

    @Test
    public void doesNotPassForNoAuthentication () {
        Request request = new RequestParser().parseRequest("GET /page HTTP/1.1\n");
        assertFalse(authentication.isAuthenticated(request));
    }

    @Test
    public void doesNotPassWithIncorrectAuthentication() {
        Request request = new RequestParser().parseRequest("GET /page HTTP/1.1\nAuthorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        assertFalse(authentication.isAuthenticated(request));
    }

    @Test
    public void doesNotPassWithIncorrectInfo() {
        Request request = new RequestParser().parseRequest("GET /page HTTP/1.1\nAuthorization: Basic " + Base64.getEncoder().encodeToString("dan:isCool".getBytes()));
        assertFalse(authentication.isAuthenticated(request));
    }

    @Test
    public void passesWithCorrectInfo() {
        Request request = new RequestParser().parseRequest("GET /page HTTP/1.1\nAuthorization: Basic " + Base64.getEncoder().encodeToString("username:password".getBytes()));
        assertTrue(authentication.isAuthenticated(request));
    }
}
