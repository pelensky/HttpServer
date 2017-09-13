package com.pelensky.httpserver;

import java.io.*;
import java.util.regex.Pattern;

public class FileProcessor {

    private final String path = "/public/";
    private final Integer offset = 1;
    private InputStream in;
    private BufferedReader reader;

    public String readLines(String fileName) throws IOException {
        setUp(fileName);
        String line;
        StringBuilder file = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            file.append(line);
        }
        tearDown();
        return String.valueOf(file);
    }

    String readRange(String fileName, String[] data) throws IOException {
        setUp(fileName);
        Integer start = Integer.parseInt(data[1]);
        Integer end = Integer.parseInt(data[2]);
        Integer length = end + offset - start;
        char[] buffer = new char[length];
        reader.skip(start);
        reader.read(buffer, 0, length);
        tearDown();
        return String.valueOf(buffer);
    }

    private void setUp(String fileName) {
        in = getClass().getResourceAsStream(path + fileName);
        reader = new BufferedReader(new InputStreamReader(in));
    }

    private void tearDown() throws IOException {
        in.close();
        reader.close();
    }

    Integer getFileSize(String fileName) throws IOException {
        return readLines(fileName).getBytes().length;
    }

    public String getContentType(String filename) {
        String[] splitFileName = filename.split(Pattern.quote("."));
        String fileType = splitFileName[splitFileName.length - 1];
        return ContentType.list().get(fileType);
    }
}
