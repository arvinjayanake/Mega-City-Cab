package com.arvin.megacitycab.apiclient;

import com.arvin.megacitycab.config.AppConfig;
import com.arvin.megacitycab.model.Config;
import com.arvin.megacitycab.model.enums.ConfigKey;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ConfigAPIController {

    public static void updateVehicle(Config config) throws IOException {
        String url = AppConfig.API_URL_BASE + "config";
        Map<String, Object> requestBody = Map.of(
                "id", config.getId(),
                "value", config.getValue(),
                "description", config.getDescription()
        );
        ApiClient.put(url, requestBody);
    }

    public static Config getConfigById(int id) throws IOException {
        String url = AppConfig.API_URL_BASE + "config?id=" + id;
        String apiResponse = ApiClient.get(url);
        return new Gson().fromJson(apiResponse, Config.class);
    }

    public static List<Config> getAllConfigs() throws IOException {
        String url = AppConfig.API_URL_BASE + "configs";
        String apiResponse = ApiClient.get(url);
        Type configListType = new TypeToken<List<Config>>() {
        }.getType();
        return new Gson().fromJson(apiResponse, configListType);
    }

    public static Config getTaxConfig() throws IOException {
        List<Config> configs = getAllConfigs();
        for (Config c : configs) {
            if (c.getConfig_key().equals(ConfigKey.TAX.toString())) {
                return c;
            }
        }

        return null;
    }

}
