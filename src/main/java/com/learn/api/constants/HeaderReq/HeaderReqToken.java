package com.learn.api.constants.HeaderReq;

import jakarta.servlet.http.HttpServletRequest;

public class HeaderReqToken {
    public static boolean isTokenMissing(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
    }
}
