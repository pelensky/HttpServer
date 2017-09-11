package com.pelensky.httpserver.Routes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormTest {

    private Form form;

    @Before
    public void setUp() {
        form = new Form();
    }

    @Test
    public void returns200ForPost() {
        assertEquals(200, form.post().getStatusCode(), 0);
    }

    @Test
    public void returns200ForPut() {
        assertEquals(200, form.put().getStatusCode(), 0);
    }

}
