package edu.uoc.abarrena.trips.security;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityHelper {

    private String token;

    public static void setToken(String token) {
        SecurityHelper.token = token;
    }

    public static String getToken() {
        return token;
    }
}
