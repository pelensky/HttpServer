package com.pelensky.httpserver;

import com.pelensky.httpserver.Request.RequestParser;
import org.junit.Test;
import sun.rmi.runtime.Log;

import static org.junit.Assert.assertTrue;

public class LogRequestsTest {

    @Test
    public void canAddOneRequestToList() {
        LogRequests.add(new RequestParser().parseRequest("GET / HTTP/1.1\n"));
        assertTrue(LogRequests.showLogs().contains("GET / HTTP/1.1"));
    }

    @Test
    public void canLogMultiplePages() {
        LogRequests.add(new RequestParser().parseRequest("GET / HTTP/1.1\n"));
        LogRequests.add(new RequestParser().parseRequest("PUT / HTTP/1.1\n"));
        assertTrue(LogRequests.showLogs().contains("GET / HTTP/1.1\nPUT / HTTP/1.1"));
    }

    @Test
    public void canLogFileViews() {
        LogRequests.add(new RequestParser().parseRequest("PUT /file.txt HTTP/1.1\n"));
        assertTrue(LogRequests.showLogs().contains("PUT /file.txt HTTP/1.1"));
    }
}
