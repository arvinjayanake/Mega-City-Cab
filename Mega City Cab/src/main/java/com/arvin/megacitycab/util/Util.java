package com.arvin.megacitycab.util;

import java.security.SecureRandom;
import java.util.Base64;

public class Util {

    public static String generateAccessToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[16];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}
