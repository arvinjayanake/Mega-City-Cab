package com.arvin.megacitycab.apiclient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiClient {

    public static String post(String url, Map<String, Object> body) throws IOException {
        HttpURLConnection connection = createConnection(url, "POST");
        sendRequestBody(connection, body);
        return getResponse(connection);
    }
    public static String get(String url) throws IOException {
        HttpURLConnection connection = createConnection(url, "GET");
        return getResponse(connection);
    }

    public static String put(String url, Map<String, Object> body) throws IOException {
        HttpURLConnection connection = createConnection(url, "PUT");
        sendRequestBody(connection, body);
        return getResponse(connection);
    }

    public static String delete(String url, Map<String, Object> body) throws IOException {
        HttpURLConnection connection = createConnection(url, "DELETE");
        sendRequestBody(connection, body);
        return getResponse(connection);
    }

    private static void sendRequestBody(HttpURLConnection connection, Map<String, Object> body) throws IOException {
        String jsonBody = new Gson().toJson(body);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
    }

    private static HttpURLConnection createConnection(String url, String method) throws IOException {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        if (!method.equals("GET")) {
            connection.setDoOutput(true);
        }
        return connection;
    }

    private static String getResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            }
        } else {
            throw new IOException("API call failed with response code: " + responseCode);
        }
    }
}
