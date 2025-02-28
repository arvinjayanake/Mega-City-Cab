package com.arvin.megacitycab.util;

import com.arvin.megacitycab.model.base.User;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class Util {

    public static String toSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public static User requestToUser(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
            Gson gson = new Gson();
            return gson.fromJson(jsonBody.toString(), User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateAccessToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[16];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}
