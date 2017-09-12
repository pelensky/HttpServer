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

}
