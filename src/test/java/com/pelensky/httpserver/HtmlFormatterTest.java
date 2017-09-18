package com.pelensky.httpserver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlFormatterTest {

    private HtmlFormatter htmlFormatter;
    private final String expectedStart = "<!DOCTYPE html>\n<HTML>\n<HEAD>\n<TITLE>title</TITLE>\n</HEAD>\n<BODY>\n";
    private final String expectedEnd = "</BODY>\n</HTML>";


    @Before
    public void setUp() {
        htmlFormatter = new HtmlFormatter();
    }

    @Test
    public void formatsAListWithASingleValue() {
        String[] fileContents = new String[] { "file1.txt"};
        String formattedLinks = "<a href=\"/file1.txt\">file1.txt</a><br>\n";
        assertEquals(expectedStart +  formattedLinks+ expectedEnd, htmlFormatter.format("title", fileContents));
    }

    @Test
    public void formatsAListWithMultipleValuesToValidHTML() {
        String[] fileContents = new String[] { "file1.txt", "file2.jpg" };
        String formattedLinks = "<a href=\"/file1.txt\">file1.txt</a><br>\n<a href=\"/file2.jpg\">file2.jpg</a><br>\n";
        assertEquals(expectedStart +  formattedLinks+ expectedEnd, htmlFormatter.format("title", fileContents));
    }
}
