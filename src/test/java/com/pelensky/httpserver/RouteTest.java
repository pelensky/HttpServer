package com.pelensky.httpserver;

import com.pelensky.httpserver.Validations.Routes;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteTest {

    @Test
    public void checksForValidRoute() {
        String input = "/";
        assertTrue(Routes.containsValidRoute(input));
    }

    @Test
    public void checksForInvalidRout() {
        String input = "OPTIONS /foobar HTTP/1.1\n";
        assertFalse(Routes.containsValidRoute(input));
    }
}
