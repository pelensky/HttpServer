package com.pelensky.httpserver.Response;

import java.util.Map;

public class Response {
    private final Integer statusCode;
    private Map<String, String> responseHeader;
    private String body;


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

    Response(Integer statusCode, Map<String, String> responseHeader, String body){
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

    public String getBody() {
        return body;
    }
}
