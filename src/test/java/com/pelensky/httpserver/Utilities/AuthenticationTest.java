package com.pelensky.httpserver.Utilities;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import com.pelensky.httpserver.Utilities.Authentication;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
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
    public void doesNotPassForNoAuthentication () throws UnsupportedEncodingException {
        Request request = new RequestParser().parseRequest("GET /page HTTP/1.1\n");
        assertFalse(authentication.isAuthenticated(request));
    }

    @Test
    public void doesNotPassWithIncorrectAuthentication() throws UnsupportedEncodingException {
        Request request = new RequestParser().parseRequest("GET /page HTTP/1.1\nAuthorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        assertFalse(authentication.isAuthenticated(request));
    }

    @Test
    public void doesNotPassWithIncorrectInfo() throws UnsupportedEncodingException {
        Request request = new RequestParser().parseRequest("GET /page HTTP/1.1\nAuthorization: Basic " + Base64.getEncoder().encodeToString("dan:isCool".getBytes()));
        assertFalse(authentication.isAuthenticated(request));
    }

    @Test
    public void passesWithCorrectInfo() throws UnsupportedEncodingException {
        Request request = new RequestParser().parseRequest("GET /page HTTP/1.1\nAuthorization: Basic " + Base64.getEncoder().encodeToString("username:password".getBytes()));
        assertTrue(authentication.isAuthenticated(request));
    }
}
