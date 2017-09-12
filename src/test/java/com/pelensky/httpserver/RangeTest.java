package com.pelensky.httpserver;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RangeTest {

    private Range range;

    @Before
    public void setUp() {
        Request request = new RequestParser("GET /partial_content.txt HTTP/1.1\nRange: bytes=0-4").parseRequest();
        range = new Range(request);
    }

    @Test
    public void splitRange() throws IOException {
        range.getRangeInfo();
        assertEquals("This", range.getResponseBody());
    }

    @Test
    public void getResponseHeader() throws IOException {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Range", "bytes 0-4/76");
        range.getRangeInfo();
        assertEquals(header, range.getResponseHeader());
    }

    @Test
    public void endOfRange() throws IOException {
        Request request = new RequestParser("GET /partial_content.txt HTTP/1.1\nRange: bytes=0-0,-6").parseRequest();
        Range range = new Range(request);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Range", "bytes 70-76/76");
        range.getRangeInfo();
        assertEquals(header, range.getResponseHeader());
    }
}
