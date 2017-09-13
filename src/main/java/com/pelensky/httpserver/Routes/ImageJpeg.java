package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageJpeg implements Route {

    @Override
    public String route() {
        return "image.jpeg";
    }

    @Override
    public Response get(Request request) throws IOException {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", new FileProcessor().getContentType(route()));
        return new Response(200, header);
    }
}
