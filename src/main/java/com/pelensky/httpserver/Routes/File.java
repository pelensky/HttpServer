package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.ContentType;
import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.File.Range;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class File extends Route {
    @Override
    public String route() {
        return "file";
    }

    @Override
    public Response get(Request request) throws IOException {
        Integer statusCode;
        Map<String, String> header = new HashMap<>();
        byte[] body;
        if (request.getHeaders().get("Range") != null) {
            statusCode = 206;
            Range range = new Range(request);
            header = range.getRangeHeaders();
            body = range.getRangeBody();
        } else {
            statusCode = 200;
            body = new FileProcessor().readLines((request.getFileType() != null) ? request.getUri() + "." + request.getFileType() : request.getUri());
        }
        header.put("Content-Type", new ContentType().list().get(request.getFileType()));
        return new Response(statusCode, header, body);
    }

}

