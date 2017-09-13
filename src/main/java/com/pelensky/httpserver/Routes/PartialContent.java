package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.ContentType;
import com.pelensky.httpserver.File.Range;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;
import java.util.Map;

public class PartialContent implements Route {
    @Override
    public String route() {
        return "partial_content";
    }

    @Override
    public Response get(Request request) throws IOException {
        Range range = new Range(request);
        Map<String, String> header = range.getRangeHeaders();
        header.put("Content-Type", ContentType.list().get(request.getFileType()));
        return new Response(206, header, range.getRangeBody());
    }
}
