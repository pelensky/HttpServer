package com.pelensky.httpserver.File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileProcessor {

    private final String path = "/public/";
    private final Integer offset = 1;
    private InputStream in;
    private BufferedReader reader;

    public String readLines(String fileName) throws IOException {
        setUpText(fileName);
        String line;
        StringBuilder file = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            file.append(line);
        }
        tearDownText();
        return String.valueOf(file);
    }

    public String readRange(String fileName, String[] data) throws IOException {
        setUpText(fileName);
        Integer start = Integer.parseInt(data[1]);
        Integer end = Integer.parseInt(data[2]);
        Integer length = end + offset - start;
        char[] buffer = new char[length];
        reader.skip(start);
        reader.read(buffer, 0, length);
        tearDownText();
        return String.valueOf(buffer);
    }

    public byte[] readImage(String fileName, String fileType) throws IOException {
        String filePath = path + fileName + "." + fileType;
        BufferedImage image = setUpImage(filePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, fileType, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        tearDownImage(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void tearDownImage(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        in.close();
        byteArrayOutputStream.close();
    }

    private BufferedImage setUpImage(String filePath) throws IOException {
        in = getClass().getResourceAsStream(filePath);
        return ImageIO.read(in);
    }

    private void setUpText(String fileName) {
        in = getClass().getResourceAsStream(path + fileName);
        reader = new BufferedReader(new InputStreamReader(in));
    }

    private void tearDownText() throws IOException {
        in.close();
        reader.close();
    }

    Integer getFileSize(String fileName) throws IOException {
        return readLines(fileName).getBytes().length;
    }

}
