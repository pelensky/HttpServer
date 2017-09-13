package com.pelensky.httpserver.Response;

import java.util.HashMap;

class Status {

    static HashMap<Integer, String> codes() {
        HashMap<Integer, String> statusCodes = new HashMap<>();
        statusCodes.put(200, "HTTP/1.1 200 OK");
        statusCodes.put(206, "HTTP/1.1 206 Partial Content");
        statusCodes.put(302, "HTTP/1.1 302 Found");
        statusCodes.put(404, "HTTP/1.1 404 Not Found");
        statusCodes.put(405, "HTTP/1.1 405 Method Not Allowed");
        statusCodes.put(418, "HTTP/1.1 418 I'm a teapot");
        return statusCodes;
    }
}
