package com.pelensky.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileProcessor {

    private final String path = "/public/";

    public String readLines(String fileName) throws IOException {
        InputStream in = getClass().getResourceAsStream(path + fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        StringBuilder file = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            file.append(line);
        }
        in.close();
        reader.close();
        return String.valueOf(file);
    }

    Integer getFileSize(String fileName) throws IOException {
       return readLines(fileName).getBytes().length;
    }

    String readRange(String fileName, String[] data) throws IOException {
        InputStream in = getClass().getResourceAsStream(path + fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        int start = Integer.parseInt(data[1]);
        int end = Integer.parseInt(data[2]);
        int length = end - start;
        char[] buffer = new char[length];
        reader.skip(start);
        reader.read(buffer, 0, length);
        in.close();
        reader.close();
        return String.valueOf(buffer);
    }
}
