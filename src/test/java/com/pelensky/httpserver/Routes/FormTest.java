package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
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
        Request request = new Request("POST", "/form", null, null, null);
        assertEquals(200, form.post(request).getStatusCode(), 0);
    }

    @Test
    public void returns200ForPut() {
        Request request = new Request("PUT", "/form", null, null, null);
        assertEquals(200, form.put(request).getStatusCode(), 0);
    }

}
