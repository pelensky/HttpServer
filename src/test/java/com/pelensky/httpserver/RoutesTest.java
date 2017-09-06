package com.pelensky.httpserver;

import com.pelensky.httpserver.Validations.Routes;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoutesTest {

    @Test
    public void getsTheRouteFromARequest() {
        String input = "OPTIONS / HTTP/1.1\n";
        assertEquals("/", Routes.getRoute(input));
    }

    @Test
    public void getsADifferentRoute() {
        String input = "OPTIONS /foobar HTTP/1.1\n";
        assertEquals("/foobar", Routes.getRoute(input));
    }

    @Test
    public void checksForValidRoute() {
        String input = "OPTIONS / HTTP/1.1\n";
        assertTrue(Routes.containsValidRoute(input));
    }

    @Test
    public void checksForInvalidRout() {
        String input = "OPTIONS /foobar HTTP/1.1\n";
        assertFalse(Routes.containsValidRoute(input));
    }
}
