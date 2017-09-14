package com.pelensky.httpserver.File;

import com.pelensky.httpserver.Request.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Range {

    private final Request request;
    private final FileProcessor fileProcessor = new FileProcessor();
    private final String routeWithoutPath;

    public Range(Request request) {
        this.request = request;
        this.routeWithoutPath = request.getUri().substring(1);
    }

    public Map<String, String> getRangeHeaders() throws IOException {
        String[] rangeRequest = splitRangeRequest();
        Map<String, String> responseHeader = new HashMap<>();
        responseHeader.put("Content-Range", rangeRequest[0] + " " + rangeRequest[1] + "-" + rangeRequest[2] + "/" + String.valueOf(getFileSize()));
        return responseHeader;
    }

    private Integer getFileSize() throws IOException {
        return fileProcessor.getFileSize(routeWithoutPath);
    }

   public String getRangeBody() throws IOException {
        return fileProcessor.readRange(routeWithoutPath, splitRangeRequest());
   }

    private String[] splitRangeRequest() throws IOException {
        String[] requestTypeAndData = request.getHeaders().get("Range").split("=");
        String[] startAndEndOfRange = requestTypeAndData[1].split("-");
        if (startAndEndOfRange[0].isEmpty()) startAndEndOfRange = setStartAndEndOfRange(startAndEndOfRange);
        if (startAndEndOfRange.length == 1) startAndEndOfRange = keepStartChangeEndToEndOfFile(startAndEndOfRange);
        return new String[]{requestTypeAndData[0], startAndEndOfRange[0], startAndEndOfRange[1]};
    }

    private String[] setStartAndEndOfRange(String[] startAndEndOfRange) throws IOException {
        startAndEndOfRange[0] = String.valueOf(getFileSize() + 1 - Integer.valueOf(startAndEndOfRange[1]));
        startAndEndOfRange[1] = String.valueOf(getFileSize());
        return startAndEndOfRange;
    }

    private String[] keepStartChangeEndToEndOfFile(String[] startAndEndOfRange) throws IOException {
        return new String[] {startAndEndOfRange[0], String.valueOf(getFileSize())};
    }

}
