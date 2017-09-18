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
        assertEquals("<a href=\"file1\">file1</a><br><a href=\"file2\">file2</a><br><a href=\"image.gif\">image.gif</a><br><a href=\"image.jpeg\">image.jpeg</a><br><a href=\"image.png\">image.png</a><br><a href=\"partial_content.txt\">partial_content.txt</a><br><a href=\"patch-content.txt\">patch-content.txt</a><br><a href=\"text-file.txt\">text-file.txt</a><br>", new String(fileProcessor.listDirectoryContents()));
    }

}
