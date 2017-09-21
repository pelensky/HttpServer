package com.pelensky.httpserver.Utilities;

import com.pelensky.httpserver.Request.Request;
import com.pelensky.httpserver.Request.RequestHeader;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Middleware {
    private final List<String[]> list = new ArrayList<>();

    public void add(String route, String username, String password) {
        String[] authentication = new String[] {route, username, password};
        list.add(authentication);
    }

    public boolean isAuthenticationRequired(Request request) {
        for (String[] route : list) {
            if (route[0].equals(request.getUri())) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthenticated(Request request) {
        if (request.hasAuthorization()) {
            for (String[] route : list) {
                if (route[0].equals(request.getUri())) {
                    String[] usernameAndPassword = decodeUsernameAndPassword(request);
                    return route[1].equals(usernameAndPassword[0]) && route[2].equals(usernameAndPassword[1]);
                }
            }
        }
        return false;
    }

    private String[] decodeUsernameAndPassword(Request request) {
        String[] authorizationDetails = request.getHeaders().get(RequestHeader.AUTHORIZATION.header()).split(" ");
        return new String(Base64.getDecoder().decode(authorizationDetails[1])).split(":");
    }

    List<String[]> getList() {
        return list;
    }

}
