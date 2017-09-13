package com.pelensky.httpserver;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RangeTest {

    private Range range;

    @Test
    public void splitRange() throws IOException {
        Request request = new RequestParser("GET /partial_content.txt HTTP/1.1\nRange: bytes=0-4").parseRequest();
        range = new Range(request);
        assertEquals("This ", range.getRangeBody());
    }

    @Test
    public void getResponseHeader() throws IOException {
        Request request = new RequestParser("GET /partial_content.txt HTTP/1.1\nRange: bytes=0-4").parseRequest();
        range = new Range(request);
        assertEquals("bytes 0-4/76", range.getRangeHeaders().get("Content-Range"));
    }

    @Test
    public void endOfRange() throws IOException {
        Request request = new RequestParser("GET /partial_content.txt HTTP/1.1\nRange: bytes=-6").parseRequest();
        Range range = new Range(request);
        assertEquals("bytes 71-76/76", range.getRangeHeaders().get("Content-Range"));
    }

    @Test
    public void startOfRange() throws IOException {
        Request request = new RequestParser("GET /partial_content.txt HTTP/1.1\nRange: bytes=4-").parseRequest();
        Range range = new Range(request);
        assertEquals("bytes 4-76/76", range.getRangeHeaders().get("Content-Range"));
    }
}
