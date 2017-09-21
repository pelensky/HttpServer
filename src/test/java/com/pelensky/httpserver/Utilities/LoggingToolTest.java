package com.pelensky.httpserver.Utilities;

import com.pelensky.httpserver.Utilities.LoggingTool;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class LoggingToolTest {

    @Test
    public void canAddOneRequestToList() throws IOException {
        LoggingTool.setUp();
        LoggingTool.logRequest("Hello");
        assertTrue(new String(LoggingTool.showLogs()).contains("Hello"));
    }

}
