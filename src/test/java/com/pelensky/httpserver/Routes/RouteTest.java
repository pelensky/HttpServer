package com.pelensky.httpserver.Routes;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
