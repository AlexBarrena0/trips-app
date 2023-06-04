package edu.uoc.abarrena.trips.application.security;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityHelper {

    private String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SecurityHelper.token = token;
    }
}
