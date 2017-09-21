package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.File.ContentType;
import com.pelensky.httpserver.Response.ResponseHeader;
import com.pelensky.httpserver.Utilities.ETag;
import com.pelensky.httpserver.File.FileProcessor;
import com.pelensky.httpserver.File.Range;
import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Response.Response;
import com.pelensky.httpserver.Response.Status;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class File extends Route {

    @Override
    public boolean respondsTo(Request request) {
        return new FileProcessor().doesFileExistInDirectory(request.findFileName());
    }

    @Override
    public Response get(Request request) throws IOException {
        if (request.hasRange()) {
         return getPartialFile(request);
        }
        return getEntireFile(request);
    }

    @Override
    public Response patch(Request request) throws IOException, NoSuchAlgorithmException {
        Integer statusCode = Status.METHOD_NOT_ALLOWED.code();
        Map<String, String> headers = new HashMap<>();
        if (request.isAFile()) {
            if (request.findETag().equals(createETag(request.findFileName()))) {
                statusCode = Status.NO_CONTENT.code();
                new FileProcessor().patchFile(request.findFileName(), request.getBody());
                headers.put(ResponseHeader.ETAG.header(), createETag(request.findFileName()));
            }
        }
        return new Response(statusCode, headers);
    }

    private Response getPartialFile(Request request) throws IOException {
        Map <String, String> header = new Range(request).getRangeHeaders();
        addContentTypeToHeader(request, header);
        return new Response(Status.PARTIAL_CONTENT.code(), header, new Range(request).getRangeBody());
    }

    private Response getEntireFile(Request request) throws IOException {
        Map<String, String> header = new HashMap<>();
        addContentTypeToHeader(request, header);
        return new Response(Status.OK.code(), header, new FileProcessor().readEntireFile((request.getFileType() != null) ? request.findFileName() : request.getUri()));
    }

    private void addContentTypeToHeader(Request request, Map<String, String> header) {
        header.put("Content-Type", new ContentType().list().get(request.getFileType()));
    }

    private String createETag(String fileName) throws NoSuchAlgorithmException, IOException {
        return new ETag().createETagFromFileContents(new FileProcessor().readEntireFile(fileName));
    }

}

