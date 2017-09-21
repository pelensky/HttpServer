package com.pelensky.httpserver.File;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestHeader;

import java.io.IOException;
import java.util.Arrays;
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
        String[] rangeRequest = splitRangeRequest();
        Map<String, String> responseHeader = new HashMap<>();
        responseHeader.put("Content-Range", rangeRequest[0] + " " + rangeRequest[1] + "-" + rangeRequest[2] + "/" + String.valueOf(lastByte()));
        return responseHeader;
    }

    private Integer lastByte() throws IOException {
        return getFileSize() - offset;
    }

    private Integer getFileSize() throws IOException {
        return fileProcessor.getFileSize(fileNameAndType);
    }

   public byte[] getRangeBody() throws IOException {
        return fileProcessor.readRange(fileNameAndType, splitRangeRequest());
   }

    private String[] splitRangeRequest() throws IOException {
        String[] requestTypeAndData = request.getHeaders().get(RequestHeader.RANGE.header()).split("=");
        String[] startAndEndOfRange = requestTypeAndData[1].split("-");
        if (startAndEndOfRange[0].isEmpty()) startAndEndOfRange = setStartAndEndOfRange(startAndEndOfRange);
        if (startAndEndOfRange.length == 1) startAndEndOfRange = keepStartChangeEndToEndOfFile(startAndEndOfRange);
        return new String[]{requestTypeAndData[0], startAndEndOfRange[0], startAndEndOfRange[1]};
    }

    private String[] setStartAndEndOfRange(String[] startAndEndOfRange) throws IOException {
        int[] data = new int[2];
        data[0] = lastByte() + offset - Integer.valueOf(startAndEndOfRange[1]);
        data[1] = lastByte();
        return Arrays.stream(data).mapToObj(String::valueOf).toArray(String[]::new);
    }

    private String[] keepStartChangeEndToEndOfFile(String[] startAndEndOfRange) throws IOException {
        return new String[] {startAndEndOfRange[0], String.valueOf(lastByte())};
    }

}
