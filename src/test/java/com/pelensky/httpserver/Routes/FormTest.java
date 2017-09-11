package com.pelensky.httpserver.Routes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormTest {

    Form form;

    @Before
    public void setUp() {
        form = new Form();
    }

    @Test
    public void returns200ForPost() {
        assertEquals("HTTP/1.1 200 OK", form.post());
    }

    @Test
    public void returns200ForPut() {
        assertEquals("HTTP/1.1 200 OK", form.put());
    }

}
