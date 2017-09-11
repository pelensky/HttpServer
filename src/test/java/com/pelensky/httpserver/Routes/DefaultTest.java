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
        assertEquals(200, defaultRoute.get().getStatusCode(), 0);
    }

    @Test
    public void unknownReturns400Response() {
        assertEquals(405, defaultRoute.post().getStatusCode(), 0);
    }

}
