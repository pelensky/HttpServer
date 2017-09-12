package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Range;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;
import java.util.Map;

public class PartialContent implements Route {
    @Override
    public String route() {
        return "/partial_content.txt";
    }

    @Override
    public Response get(Request request) throws IOException {
        Range range = new Range(request);
        range.getRangeInfo();
        Map<String, String> header = range.getResponseHeader();
        String body = range.getResponseBody();
        return new Response(206, header, body);
    }
}
