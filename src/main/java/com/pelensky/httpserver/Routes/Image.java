package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.ContentType;
import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Image implements Route {

    @Override
    public String route() {
        return "image";
    }

    @Override
    public Response get(Request request) throws IOException {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", ContentType.list().get(request.getFileType()));
        byte[] byteArray = new FileProcessor().readLines(route() + "." + request.getFileType());
        return new Response(200, header, new String(byteArray));
    }
}
