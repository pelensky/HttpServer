package com.pelensky.httpserver.Server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandLineArgumentsParserTest {
    private CommandLineArgumentParser parser;
    private String[] args;

    public void setUp(String[] args) {
        parser = new CommandLineArgumentParser(args);
    }

    @Test
    public void parsesPortNumberAsInteger() {
        setUp(new String[]{"-p", "9090", "-d", "/Users/dan/Server/cob_spec/public/"});
        assertEquals(parser.findPort(), 9090);
    }

    @Test
    public void parsesOtherPortNumberAsInteger() {
        setUp(new String[]{"-p", "8080", "-d", "/Users/dan/Server/cob_spec/public/"});
        assertEquals(parser.findPort(), 8080);
    }

    @Test
    public void findsPortNumberIfSecond() {
        setUp(new String[]{"-d", "/Users/dan/Server/cob_spec/public/", "-p", "8080"});
        assertEquals(parser.findPort(), 8080);
    }

    @Test
    public void returnsDefaultPortIfNotSpecified() {
        setUp(new String[0]);
        assertEquals(parser.findPort(), 5000);
    }

    @Test
    public void parsesDirectory() {
        setUp(new String[]{"-p", "9090", "-d", "/Users/dan/Server/cob_spec/public/"});
        assertEquals(parser.findDirectory(), "/Users/dan/Server/cob_spec/public/");
    }

    @Test
    public void parsesOtherDirectory() {
        setUp(new String[]{"-p", "9090", "-d", "/Users/dan/Server/cob_spec/"});
        assertEquals(parser.findDirectory(), "/Users/dan/Server/cob_spec/");
    }

    @Test
    public void parsesDirectoryIfFirst() {
        setUp(new String[]{"-d", "/Users/dan/Server/cob_spec/public/", "-p", "9090"});
        assertEquals(parser.findDirectory(), "/Users/dan/Server/cob_spec/public/");
    }

    @Test
    public void returnsDefaultDirectoryIfNotSpecified() {
        setUp(new String[0]);
        assertEquals(parser.findDirectory(), "PUBLIC_DIR");
    }
}
