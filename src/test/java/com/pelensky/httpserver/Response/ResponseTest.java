package com.pelensky.httpserver.Response;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ResponseTest {

    private Response response;

    @Test
    public void responseWithOnlyAStatusCode() {
        response = new Response(200);
        assertEquals(200, response.getStatusCode(), 0);
    }

    @Test
    public void responseCanHaveAStatusCodeAndHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Location", "/");
        response = new Response(302, header);
        assertEquals("/", response.getResponseHeader().get("Location"));
    }

    @Test
    public void responseCanHaveStatusCodeHeaderAndBody() {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Length", "32");
        response = new Response(200, header, "data=fatcat".getBytes());
        assertEquals("data=fatcat", new String(response.getBody()));
    }
}
