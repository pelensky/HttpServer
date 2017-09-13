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

    @Test
    public void readsARangeFromAFile() throws IOException {
        String[] data = new String[] { "bytes", "0", "4" };
        String partialFile = fileProcessor.readRange("partial_content.txt", data);
        assertEquals("This ", partialFile);
    }

    @Test
    public void getsContentType() {
        assertEquals("text/plain", fileProcessor.getContentType("/partial_content.txt"));
    }

    @Test
    public void getsContentTypeForImages() {
        assertEquals("image/jpeg", fileProcessor.getContentType("/image.jpeg"));
        assertEquals("image/gif", fileProcessor.getContentType("/image.gif"));
        assertEquals("image/png", fileProcessor.getContentType("/image.png"));
    }

}
