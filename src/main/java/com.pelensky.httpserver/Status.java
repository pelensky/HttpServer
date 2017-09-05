package com.pelensky.httpserver;

        import java.util.HashMap;

class Status {

    static HashMap<Integer, String> codes() {
        HashMap<Integer, String> statusCodes = new HashMap<Integer, String>();
        statusCodes.put(404, "HTTP/1.1 404 Not Found");
        statusCodes.put(200, "HTTP/1.1 200 OK");
        return statusCodes;
    }

}
