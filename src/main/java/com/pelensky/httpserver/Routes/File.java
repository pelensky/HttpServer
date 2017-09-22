package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.ETag;
import com.pelensky.httpserver.File.ContentType;
import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.File.Range;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
            body = new FileProcessor().readEntireFile((request.getFileType() != null) ? request.findFileName() : request.getUri());
        }
        header.put("Content-Type", new ContentType().list().get(request.getFileType()));
        return new Response(statusCode, header, body);
    }

    @Override
    public Response patch(Request request) throws IOException, NoSuchAlgorithmException {
        Integer statusCode = 405;
        Map<String, String> headers = new HashMap<>();
        if (request.isAFile()) {
            if (request.findETag().equals(createETagFromFile(request.findFileName()))) {
                new FileProcessor().patchFile(request.findFileName(), request.getBody());
                statusCode = 204;
                headers.put("ETag", createETagFromFile(request.findFileName()));
            }
        }
        return new Response(statusCode, headers);
    }

    private String createETagFromFile(String fileName) throws NoSuchAlgorithmException, IOException {
        return new ETag().createETagFromFileContents(new FileProcessor().readEntireFile(fileName));
    }


}

