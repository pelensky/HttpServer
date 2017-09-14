package com.pelensky.httpserver.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileProcessor {

    private final String publicFolderPath = "/public/";
    private final Integer offset = 1;

    public byte[] readLines(String fileName) throws IOException {
        Path path = Paths.get("." + publicFolderPath + fileName);
        return Files.readAllBytes(path);
    }

    public byte[] readRange(String fileName, String[] data) throws IOException {
        Integer start = Integer.parseInt(data[1]);
        Integer end = Integer.parseInt(data[2]) + offset;
        Path pathA = Paths.get("." + publicFolderPath + fileName);
        byte[] ba = Files.readAllBytes(pathA);
        return Arrays.copyOfRange(ba, start, end); //TODO find a different way of doing this
    }

    Integer getFileSize(String fileName) throws IOException {
        return readLines(fileName).length;
    }

}
