package com.pelensky.httpserver.Response;

import java.util.Map;

public class Response {
    private final Integer statusCode;
    private final Map<String, String> responseHeader;
    private final Map<String, String> body;


    public Response(Integer statusCode) {
        this.statusCode = statusCode;
        this.responseHeader = null;
        this.body = null;
    }

    public Response(Integer statusCode, Map<String, String> responseHeader) {
        this.statusCode = statusCode;
        this.responseHeader = responseHeader;
        this.body = null;
    }

    public Response(Integer statusCode, Map<String, String> responseHeader, Map<String, String> body){
        this.statusCode = statusCode;
        this.responseHeader = responseHeader;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public Map<String,String> getResponseHeader() {
        return responseHeader;
    }

    public Map<String, String> getBody() {
        return body;
    }
}
