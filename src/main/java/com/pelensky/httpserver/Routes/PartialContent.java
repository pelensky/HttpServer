package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.Range;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;

public class PartialContent implements Route {
    @Override
    public String route() {
        return "partial_content.txt";
    }

    @Override
    public Response get(Request request) throws IOException {
        Range range = new Range(request);
        return new Response(206, range.getRangeHeaders(), range.getRangeBody());
    }
}
