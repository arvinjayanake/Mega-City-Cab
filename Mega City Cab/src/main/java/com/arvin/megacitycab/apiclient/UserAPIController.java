package com.arvin.megacitycab.apiclient;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class UserAPIController {

    public static User userOtpVerify(int id, String otp) throws IOException {
        String url = Config.API_URL_BASE + "user/otp/verify";
        Map<String, Object> requestBody = Map.of(
                "id", id,
                "otp", otp
        );
        String apiResponse = ApiClient.post(url, requestBody);
        return new Gson().fromJson(apiResponse, User.class);
    }

    public static User getUserById(int id) throws IOException {
        String url = Config.API_URL_BASE + "user?id=" + id;
        String apiResponse = ApiClient.get(url);
        return new Gson().fromJson(apiResponse, User.class);
    }

    public static List<User> getAllAdmins() throws IOException {
        String url = Config.API_URL_BASE + "users?type=3";
        String apiResponse = ApiClient.get(url);
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>() {}.getType();
        return gson.fromJson(apiResponse, userListType);
    }

    public static List<User> getAllDrivers() throws IOException {
        String url = Config.API_URL_BASE + "users?type=2";
        String apiResponse = ApiClient.get(url);
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>() {}.getType();
        return gson.fromJson(apiResponse, userListType);
    }

    public static List<User> getAllCustomers() throws IOException {
        String url = Config.API_URL_BASE + "users?type=1";
        String apiResponse = ApiClient.get(url);
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>() {}.getType();
        return gson.fromJson(apiResponse, userListType);
    }

    public static User updateUser(User user) throws IOException {
        String url = Config.API_URL_BASE + "user";
        Map<String, Object> requestBody = new java.util.HashMap<>(Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "nic", user.getNic(),
                "address", user.getAddress(),
                "email", user.getEmail(),
                "mobile", user.getMobile(),
                "is_verified", user.getIs_verified()
        ));

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            requestBody.put("password", Util.toSHA256(user.getPassword()));
        }

        String apiResponse = ApiClient.put(url, requestBody);
        return new Gson().fromJson(apiResponse, User.class);
    }

    public static User createUser(User user) throws IOException {
        String url = Config.API_URL_BASE + "user";
        Map<String, Object> requestBody = new java.util.HashMap<>(Map.of(
                "name", user.getName(),
                "nic", user.getNic(),
                "email", user.getEmail(),
                "address", user.getAddress(),
                "mobile", user.getMobile(),
                "verification_code", user.getVerification_code(),
                "is_verified", user.getIs_verified(),
                "type", user.getType(),
                "password", user.getPassword()
        ));

        String apiResponse = ApiClient.post(url, requestBody);
        return new Gson().fromJson(apiResponse, User.class);
    }

    public static void deleteUser(String id) throws IOException {
        String url = Config.API_URL_BASE + "user";
        Map<String, Object> requestBody = Map.of("id", id);
        ApiClient.delete(url, requestBody);
    }

    public static User login(String email, String password) throws IOException {
        String url = Config.API_URL_BASE + "user/login";
        Map<String, Object> requestBody = Map.of(
                "email", email,
                "password", Util.toSHA256(password)
        );
        String apiResponse = ApiClient.post(url, requestBody);
        return new Gson().fromJson(apiResponse, User.class);
    }

}
