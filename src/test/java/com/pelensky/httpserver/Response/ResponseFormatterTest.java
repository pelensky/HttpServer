package com.pelensky.httpserver.Response;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ResponseFormatterTest {

    @Test
    public void processesABasicGetRequestResponse() {
        Response response = new Response(200);
        assertEquals("HTTP/1.1 200 OK", new ResponseFormatter().format(response));
    }

    @Test
    public void processA404Response() {
        Response response = new Response(404);
        assertEquals("HTTP/1.1 404 Not Found", new ResponseFormatter().format(response));
    }

    @Test
    public void processesAResponseWithHeaders() {
        Map<String, String> headers= new HashMap<>();
        headers.put("Location", "/");
        Response response = new Response(302, headers);
        assertEquals("HTTP/1.1 302 Found\nLocation: /", new ResponseFormatter().format(response));
    }

    @Test
    public void processesAResponseWithABody(){
        Response response = new Response(200, null, "data=fatcat\n");
        assertEquals("HTTP/1.1 200 OK\nContent-Length: 12\n\ndata=fatcat", new ResponseFormatter().format(response));
    }
}
