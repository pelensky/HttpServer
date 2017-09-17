package com.pelensky.httpserver.File;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileProcessor {

    private final Integer offset = 1;

    public byte[] readLines(String fileName) throws IOException {
        return Files.readAllBytes(getPath(fileName));
    }

    public byte[] readRange(String fileName, String[] data) throws IOException {
        Integer start = Integer.parseInt(data[1]);
        Integer end = Integer.parseInt(data[2]) + offset;
        byte[] bytes = Files.readAllBytes(getPath(fileName));
        return Arrays.copyOfRange(bytes, start, end); //TODO find a different way of doing this
    }

    Integer getFileSize(String fileName) throws IOException {
        return readLines(fileName).length;
    }

    public Boolean directoryContainsFile(String filename) {
       return Files.exists(getPath(filename));
    }

    private Path getPath(String fileName) {
        return Paths.get("./public/" + fileName);
    }

    public byte[] listDirectoryContents() throws IOException {
        String[] files = new File("./public/").list();
        if (files != null) {
            return String.join(System.lineSeparator(), files).getBytes();
        }
        return "".getBytes();
    }
}
