package com.pelensky.httpserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProcessor {

    String path = "src/main/resources/";

    public String readLines(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path + fileName)));
    }
}
