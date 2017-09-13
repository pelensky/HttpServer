package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.ContentType;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextFile implements Route {
    @Override
    public String route() {
        return "text-file";
    }

    @Override
    public Response get(Request request) throws IOException {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", ContentType.list().get(request.getFileType()));
        return new Response(200, header, readFile("text-file.txt"));
    }
}
