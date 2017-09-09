package com.pelensky.httpserver;

import com.pelensky.httpserver.Socket.FakeSocket;
import org.junit.Test;


import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RequestProcessorTest {
    private FakeSocket fakeSocket;
    private RequestProcessor processor = new RequestProcessor();

    @Test
    public void processorHandlesSingleLineRequest() throws IOException {
        fakeSocket = new FakeSocket("GET / HTTP/1.1\n");
        assertEquals("GET / HTTP/1.1\n", processor.getRequestFromSocket(fakeSocket));
    }

    @Test
    public void processorHandlesMultiLineRequests() throws IOException {
        fakeSocket = new FakeSocket("GET / HTTP/1.1\nTest\n");
        assertEquals("GET / HTTP/1.1\nTest\n", processor.getRequestFromSocket(fakeSocket));
    }

}

