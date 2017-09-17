package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.ContentType;
import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.File.Range;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class File implements Route {
    @Override
    public String route() {
        return "file";
    }

    @Override
    public Response get(Request request) throws IOException {
        Integer statusCode;
        Map<String, String> header = new HashMap<>();
        String body;
        if (request.getHeaders().get("Range") != null) {
            statusCode = 206;
            Range range = new Range(request);
            header = range.getRangeHeaders();
            body = range.getRangeBody();
        } else {
            statusCode = 200;
//            body = new FileProcessor().readLines((request.getFileType() != null) ? request.getUri() + "." + request.getFileType() : request.getUri());
            body = readFileAsString((request.getFileType() != null) ? request.getUri() + "." + request.getFileType() : request.getUri());
        }
        String contentType = new ContentType().list().get(request.getFileType());
//        if (contentType.contains("image")) {
//            body = "data:" + contentType + ";base64," + Base64.getUrlEncoder().encodeToString(new FileProcessor().readLines(request.getUri() + "." + request.getFileType()));
//        }
        header.put("Content-Type", contentType);
        return new Response(statusCode, header, body);
    }

    private String readFileAsString(String fileName) throws IOException {
        return new String(new FileProcessor().readLines(fileName));
    }
}

