package com.pelensky.httpserver.Response;

import java.util.HashMap;

public class Status {

    public static HashMap<Integer, String> codes() {
        HashMap<Integer, String> statusCodes = new HashMap<Integer, String>();
        statusCodes.put(200, "HTTP/1.1 200 OK");
        statusCodes.put(404, "HTTP/1.1 404 Not Found");
        statusCodes.put(405, "HTTP/1.1 405 Method Not Allowed");
        return statusCodes;
    }
}
