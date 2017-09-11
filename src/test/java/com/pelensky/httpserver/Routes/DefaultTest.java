package com.pelensky.httpserver.Routes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultTest {
    private Default defaultRoute;

    @Before
    public void setUp() {
        defaultRoute = new Default();
    }

    @Test
    public void getReturns200Response() {
        assertEquals("HTTP/1.1 200 OK", defaultRoute.get());
    }

    @Test
    public void unknownReturns400Response() {
        assertEquals("HTTP/1.1 405 Method Not Allowed", defaultRoute.post());
    }

}
