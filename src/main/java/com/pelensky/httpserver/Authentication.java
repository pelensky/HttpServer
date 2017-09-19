package com.pelensky.httpserver;

import com.pelensky.httpserver.Request.Request;

import java.util.Base64;

public class Authentication {

    private String username;
    private String password;

    public Authentication(String username, String password) {
       this.username = username;
       this.password = password;
    }

    public boolean isAuthenticated(Request request) {
        if (request.getHeaders().get("Authorization") != null) {
            String[] decodedUsernameAndPassword = decodeUsernameAndPassword(request);
            return (decodedUsernameAndPassword[0].equals(username) && decodedUsernameAndPassword[1].equals(password));
        }
        return false;
    }

    private String[] decodeUsernameAndPassword(Request request) {
        String[] authorizationDetails = request.getHeaders().get("Authorization").split(" ");
        return new String(Base64.getDecoder().decode(authorizationDetails[1])).split(":");
    }


}
