package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Routes.Routes;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteTest {

    @Test
    public void checksForValidRoute() {
        String input = "/";
        assertTrue(Routes.containsValidRoute(input));
    }

    @Test
    public void checksForInvalidRoute() {
        String input = "OPTIONS /foobar HTTP/1.1\n";
        assertFalse(Routes.containsValidRoute(input));
    }
}
