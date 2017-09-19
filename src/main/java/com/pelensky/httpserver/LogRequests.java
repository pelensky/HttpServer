package com.pelensky.httpserver;

import com.pelensky.httpserver.Request.Request;

import java.util.ArrayList;
import java.util.List;

public class LogRequests {
    private static final List<Request> logs = new ArrayList<>();

    public static void add(Request request) {
        logs.add(request);
    }

    public static String showLogs() {
        StringBuilder allRequests = new StringBuilder();
        for (Request request : logs) {
            String uri = (request.getFileType() != null) ? "/" + request.getUri() + "." + request.getFileType() : "/" + request.getUri();
            allRequests.append(request.getMethod()).append(" ").append(uri).append(" ").append(request.getHttpVersion()).append(System.lineSeparator());
        }
        return String.valueOf(allRequests).trim();
    }

}
