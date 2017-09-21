package com.pelensky.httpserver.File;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileProcessor {
    private final String path = "./public/";
    private final Integer arrayOffset = 1;

    public byte[] readEntireFile(String fileName) throws IOException {
        return readFile(fileName, 0, getFileSize(fileName));
    }

    byte[] readRange(String fileName, int[] data) throws IOException {
        Integer start = data[0];
        Integer end = data[1] + arrayOffset;
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

    public String[] getPublicFolderContents() {
        String[] files = new File(path).list();
        assert files != null;
        Arrays.sort(files);
        return files;
    }

    public byte[] patchFile(String fileName, String updatedContent) throws IOException {
        Files.write(Paths.get(path + fileName), updatedContent.getBytes());
        return readEntireFile(fileName);
    }

    void deleteFile(String fileName) throws IOException {
        Files.delete(Paths.get(path + fileName));
    }

}
