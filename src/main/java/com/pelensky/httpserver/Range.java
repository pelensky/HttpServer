package com.pelensky.httpserver;

import com.pelensky.httpserver.Request.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Range {

    private final Request request;
    private final Map<String, String> responseHeader = new HashMap<>();
    private String responseBody;
    private final FileProcessor fileProcessor = new FileProcessor();
    private final String route;

    public Range(Request request) {
        this.request = request;
        this.route = request.getUri();
    }

    public void getRangeInfo() throws IOException {
        String[] rangeRequest = splitRangeRequest();
        String value = rangeRequest[0] + " " + rangeRequest[1] + "-" + rangeRequest[2] + "/" + String.valueOf(getFileSize());
        responseHeader.put("Content-Range", value);
        responseHeader.put("Content-Type", fileProcessor.getContentType(route.substring(1)));
        responseBody = getRequestedRange(rangeRequest);
    }

    private Integer getFileSize() throws IOException {
        return fileProcessor.getFileSize(route.substring(1));
    }

    private String getRequestedRange(String[] requestDetails) throws IOException {
        return fileProcessor.readRange(route.substring(1), requestDetails);
   }

    private String[] splitRangeRequest() throws IOException {
        String[] splitUnitFromStartAndEnd = request.getHeaders().get("Range").split("=");
        String unit = splitUnitFromStartAndEnd[0];
        String[] splitStartAndEnd = splitUnitFromStartAndEnd[1].split("-");
        if (splitStartAndEnd[0].isEmpty()) {
            splitStartAndEnd[0] = String.valueOf(getFileSize() - Integer.valueOf(splitStartAndEnd[1]) + 1);
            splitStartAndEnd[1] = String.valueOf(getFileSize());
        }
        if (splitStartAndEnd.length == 1) {
            String[] newArray = new String[2];
            newArray[0] = splitStartAndEnd[0];
            newArray[1] = String.valueOf(getFileSize());
            splitStartAndEnd = newArray;
        }
        return new String[]{ unit, splitStartAndEnd[0], splitStartAndEnd[1]};
    }

    public Map<String,String> getResponseHeader() {
        return responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

}
