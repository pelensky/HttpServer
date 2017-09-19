package com.pelensky.httpserver;

import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class ETagTest {
    ETag ETag;

    @Before
    public void setUp() {
        ETag = new ETag();
    }

    @Test
    public void convertByteArrayToEtag() throws NoSuchAlgorithmException {
        assertEquals("dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec", ETag.createETagFromFileContents("default content".getBytes()));
    }

    @Test
    public void convertDifferentByteArrayToEtag() throws NoSuchAlgorithmException {
        assertEquals("5c36acad75b78b82be6d9cbbd6143ab7e0cc04b0", ETag.createETagFromFileContents("patched content".getBytes()));
    }

}
