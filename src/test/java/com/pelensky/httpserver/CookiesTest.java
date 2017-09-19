package com.pelensky.httpserver;

import com.pelensky.httpserver.File.Cookies;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CookiesTest {

    Cookies cookies;
    Map<String,String> headers;

    @Before
    public void setUp() {
        cookies = new Cookies();
        headers = new HashMap<>();
    }

    @Test
    public void getsASingleCookie() throws UnsupportedEncodingException {
        headers.put("Cookie", "yummy_cookie=choco");
        assertEquals("choco", cookies.getCookie(headers).get("yummy_cookie"));
    }

    @Test
    public void getsMultipleCookies() throws UnsupportedEncodingException {
        headers.put("Cookie", "yummy_cookie=choco; tasty_cookie=strawberry");
        assertEquals("choco", cookies.getCookie(headers).get("yummy_cookie"));
    }
}
