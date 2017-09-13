package com.pelensky.httpserver;

import java.io.*;
import java.util.regex.Pattern;

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
        int end = Integer.parseInt(data[2]) + 1;
        int length = end - start;
        char[] buffer = new char[length];
        reader.skip(start);
        reader.read(buffer, 0, length);
        in.close();
        reader.close();
        return String.valueOf(buffer);
    }

    public String getContentType(String filename) {
        String[] splitFileName = filename.split(Pattern.quote("."));
        String fileType = splitFileName[splitFileName.length - 1];
        return ContentType.list().get(fileType);
    }
}
