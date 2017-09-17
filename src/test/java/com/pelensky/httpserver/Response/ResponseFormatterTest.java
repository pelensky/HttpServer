package com.pelensky.httpserver.Response;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ResponseFormatterTest {

    @Test
    public void processesABasicGetRequestResponse() throws IOException {
        Response response = new Response(200);
        assertEquals("HTTP/1.1 200", stringifiedVersionOfResponse(response));
    }

    @Test
    public void processA404Response() throws IOException {
        Response response = new Response(404);
        assertEquals("HTTP/1.1 404", stringifiedVersionOfResponse(response));
    }

    @Test
    public void processesAResponseWithHeaders() throws IOException {
        Map<String, String> headers= new HashMap<>();
        headers.put("Location", "/");
        Response response = new Response(302, headers);
        assertEquals("HTTP/1.1 302\nLocation: /", stringifiedVersionOfResponse(response));
    }

    @Test
    public void processesAResponseWithABody() throws IOException {
        Response response = new Response(200, null, "data=fatcat".getBytes());
        assertEquals("HTTP/1.1 200\nContent-Length: 11\n\ndata=fatcat", stringifiedVersionOfResponse(response));
    }

    private String stringifiedVersionOfResponse(Response response) throws IOException {
        return new String(new ResponseFormatter().format(response));
    }
}
