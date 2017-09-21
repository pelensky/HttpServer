package com.pelensky.httpserver.File;

import com.pelensky.httpserver.Utilities.HtmlFormatter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileProcessor {
    private final String path = "./public/";
    private final String webTitle = "HttpServer";
    final Integer arrayOffset = 1;

    public byte[] readEntireFile(String fileName) throws IOException {
        return readFile(fileName, 0, getFileSize(fileName));
    }

    byte[] readRange(String fileName, String[] data) throws IOException {
        Integer start = Integer.parseInt(data[1]);
        Integer end = Integer.parseInt(data[2]) + arrayOffset;
        return readFile(fileName, start, end);
    }

    private byte[] readFile(String fileName, Integer start, Integer end) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(path + fileName, "r");
        byte[] buffer = new byte[end - start];
        for (int i=0; i<buffer.length; i++ ) {
            randomAccessFile.seek(start + i);
            buffer[i] = randomAccessFile.readByte();
        }
        return buffer;
    }

    Integer getFileSize(String fileName) throws IOException {
        return Math.toIntExact(Files.size(getPath(fileName)));
    }

    public Boolean doesFileExistInDirectory(String filename) {
        return Files.exists(getPath(filename));
    }

    private Path getPath(String fileName) {
        return Paths.get("./public/" + fileName);
    }

    public byte[] displayDirectoryContentsAsLinks() {
        String[] files = new File(path).list();
        assert files != null;
        Arrays.sort(files);
        return new HtmlFormatter().format(webTitle, files).getBytes();
    }

    public byte[] patchFile(String fileName, String updatedContent) throws IOException {
        Files.write(Paths.get(path + fileName), updatedContent.getBytes());
        return readEntireFile(fileName);
    }

    void deleteFile(String fileName) throws IOException {
        Files.delete(Paths.get(path + fileName));
    }

}
