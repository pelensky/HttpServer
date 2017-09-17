package com.pelensky.httpserver;

import com.pelensky.httpserver.File.FileProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileProcessorTest {
    private FileProcessor fileProcessor;

    @Before
    public void setUp() {
        fileProcessor = new FileProcessor();
    }

    @Test
    public void readsAFile() throws IOException {
        String allLines = new String(fileProcessor.readLines("file1"));
        assertEquals("file1 contents", allLines);
    }

    @Test
    public void readsARangeFromAFile() throws IOException {
        String[] data = new String[] { "bytes", "0", "4" };
        String partialFile = new String(fileProcessor.readRange("partial_content.txt", data));
        assertEquals("This ", partialFile);
    }

    @Test
    public void checksAFileExists() {
        assertTrue(fileProcessor.directoryContainsFile("partial_content.txt"));
    }

    @Test
    public void listDirectoryContents() throws IOException {
        assertEquals("file1\n" +
                "file2\n" +
                "image.gif\n" +
                "image.jpeg\n" +
                "image.png\n" +
                "partial_content.txt\n" +
                "patch-content.txt\n" +
                "text-file.txt", new String(fileProcessor.listDirectoryContents()));
    }

}
