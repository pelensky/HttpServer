package com.pelensky.httpserver;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FileProcessorTest {
    private FileProcessor fileProcessor;

    @Before
    public void setUp() {
        fileProcessor = new FileProcessor();
    }

    @Test
    public void readsAFile() throws IOException {
        String allLines = fileProcessor.readLines("file1");
        assertEquals("file1 contents", allLines);
    }

}
