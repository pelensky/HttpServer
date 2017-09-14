package com.pelensky.httpserver.File;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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

    public String readRange(String fileName, String[] data) throws IOException {
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

    public byte[] readImage(String fileName, String fileType) throws IOException {
        // TODO FIX THIS
        String filePath = path + fileName + "." + fileType;
        in = getClass().getResourceAsStream(filePath);
        BufferedImage image = ImageIO.read(in);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, fileType, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return imageInByte;
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

}
