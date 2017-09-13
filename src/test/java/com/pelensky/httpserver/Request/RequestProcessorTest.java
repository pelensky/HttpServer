package com.pelensky.httpserver.Request;

import com.pelensky.httpserver.Request.RequestProcessor;
import com.pelensky.httpserver.Socket.FakeSocket;
import org.junit.Test;


import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RequestProcessorTest {
    private FakeSocket fakeSocket;
    private final RequestProcessor processor = new RequestProcessor();

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

    @Test
    public void processorHandlesRequestWithBody() throws IOException {
        fakeSocket = new FakeSocket("POST /form HTTP/1.1\n" +
                "User-Agent: HTTPTool/1.0\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 20\n" +
                "\n" +
                "name=dan&data=fatcat");
        assertEquals("POST /form HTTP/1.1\n" +
                "User-Agent: HTTPTool/1.0\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Content-Length: 20\n" +
                "\n" +
                "name=dan&data=fatcat", processor.getRequestFromSocket(fakeSocket));
    }
}

