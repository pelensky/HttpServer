package com.pelensky.httpserver.Utilities;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class LoggingToolTest {

    @Test
    public void addARequest() throws IOException {
        LoggingTool.setUp();
        LoggingTool.logRequest("Hello");
        assertTrue(new String(LoggingTool.showLogs()).contains("Hello"));
    }

    @Test
    public void addsALoggingError() throws IOException {
        LoggingTool.setUp();
        LoggingTool.logError("Error");
        assertTrue(new String(LoggingTool.showLogs()).contains("Error"));

    }

}
