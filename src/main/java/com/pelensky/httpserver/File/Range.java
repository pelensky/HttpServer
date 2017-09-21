package com.pelensky.httpserver.File;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestHeader;
import com.pelensky.httpserver.Response.ResponseHeader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Range {

    private final Request request;
    private final FileProcessor fileProcessor = new FileProcessor();
    private final Integer offset = 1;
    private final String fileNameAndType;

    public Range(Request request) {
        this.request = request;
        this.fileNameAndType = request.getUri() + "." + request.getFileType();
    }

    public Map<String, String> getRangeHeaders() throws IOException {
        int[] rangeRequest = splitRangeRequest();
        Map<String, String> responseHeader = new HashMap<>();
        responseHeader.put(ResponseHeader.CONTENT_RANGE.header(), "bytes" + " " + rangeRequest[0] + "-" + rangeRequest[1] + "/" + String.valueOf(lastByte()));
        return responseHeader;
    }

    private Integer lastByte() throws IOException {
        return getFileSize() - offset;
    }

    private Integer getFileSize() throws IOException {
        return fileProcessor.getFileSize(fileNameAndType);
    }

    public byte[] getRangeBody() throws IOException {
        int[] splitRange = splitRangeRequest();
        return fileProcessor.readRange(fileNameAndType, splitRange);
    }

        private int[] splitRangeRequest() throws IOException {
        String[] requestTypeAndData = request.getHeaders().get(RequestHeader.RANGE.header()).split("=");
        String[] startAndEndOfRange = requestTypeAndData[1].split("-");
        int[] data = new int[2];
        if (!startAndEndOfRange[0].isEmpty() && startAndEndOfRange.length != 1) data = new int[] {Integer.parseInt(startAndEndOfRange[0]), Integer.parseInt(startAndEndOfRange[1])};
        if (startAndEndOfRange[0].isEmpty())  data = setStartAndEndOfRange(startAndEndOfRange);
        if (startAndEndOfRange.length == 1) data = keepStartChangeEndToEndOfFile(startAndEndOfRange);
        return data;
    }

    private int[] setStartAndEndOfRange(String[] startAndEndOfRange) throws IOException {
        int[] data = new int[2];
        data[0] = lastByte() + offset - Integer.valueOf(startAndEndOfRange[1]);
        data[1] = lastByte();
        return data;
    }

    private int[] keepStartChangeEndToEndOfFile(String[] startAndEndOfRange) throws IOException {
        return new int[] {Integer.parseInt(startAndEndOfRange[0]), lastByte()};
    }

}
