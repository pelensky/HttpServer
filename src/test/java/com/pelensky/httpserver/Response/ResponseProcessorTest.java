package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Socket.SocketSpy;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ResponseProcessorTest {

    @Test
    public void createsAPrintWriterUsingTheClientSocket() throws IOException {
        SocketSpy socketSpy = new SocketSpy();
        ResponseProcessor responseProcessor = new ResponseProcessor();
        responseProcessor.sendResponse(socketSpy, "test");
        assertTrue(socketSpy.getWasCalled());
    }
}
