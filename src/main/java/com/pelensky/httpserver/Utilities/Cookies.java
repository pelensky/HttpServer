package com.pelensky.httpserver.Utilities;

import java.util.HashMap;
import java.util.Map;

public class Cookies {

    public Map<String, String> getCookie(Map<String,String> headers) {
        Map<String, String> cookies = new HashMap<>();
        String requestCookies = headers.get("Cookie");
        if (requestCookies.contains(";")) {
            String[] splitCookies = requestCookies.split(";");
            for (String individualCookie : splitCookies) {
                collectCookies(cookies, individualCookie);
            }
        } else {
            collectCookies(cookies, requestCookies);
        }
        return cookies;
    }

    private void collectCookies(Map<String, String> cookies, String singleCookiePair) {
        String[] data = singleCookiePair.trim().split("=");
        for (int i = 0; i < data.length; i += 2) {
            cookies.put(data[i], data[i+1]);
        }
    }
}
