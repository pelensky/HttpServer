package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.FileProcessor;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;

import java.io.IOException;

public class File1 implements Route {
    @Override
    public String route() {
        return "/file1";
    }

    @Override
    public Response get(Request request) throws IOException {
        String fileContent = new FileProcessor().readLines("file1");
        return new Response(200, null, fileContent);
    }
}
