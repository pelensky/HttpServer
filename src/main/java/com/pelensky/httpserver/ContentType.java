package com.pelensky.httpserver;

import java.util.HashMap;
import java.util.Map;

public class ContentType {

    public static Map<String, String> list() {
        Map<String, String> contentTypes = new HashMap<>();
        contentTypes.put("txt", "text/plain");
        contentTypes.put("jpeg", "image/jpeg");
        contentTypes.put("png", "image/png");
        contentTypes.put("gif", "image/gif");
        return contentTypes;
    }
}
