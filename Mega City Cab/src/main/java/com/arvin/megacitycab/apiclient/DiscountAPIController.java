package com.arvin.megacitycab.apiclient;

import com.arvin.megacitycab.config.AppConfig;
import com.arvin.megacitycab.model.Discount;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class DiscountAPIController {

    public static void updateDiscount(Discount discount) throws IOException {
        String url = AppConfig.API_URL_BASE + "discount";
        Map<String, Object> requestBody = Map.of(
                "id", discount.getId(),
                "code", discount.getCode(),
                "type", discount.getType(),
                "value", discount.getValue(),
                "enabled", discount.getEnabled()
        );
        ApiClient.put(url, requestBody);
    }

    public static void createDiscount(Discount discount) throws IOException {
        String url = AppConfig.API_URL_BASE + "discount";
        Map<String, Object> requestBody = Map.of(
                "code", discount.getCode(),
                "type", discount.getType(),
                "value", discount.getValue(),
                "enabled", discount.getEnabled()
        );
        ApiClient.post(url, requestBody);
    }

    public static void deleteDiscountById(int id) throws IOException {
        String url = AppConfig.API_URL_BASE + "discount";
        Map<String, Object> requestBody = Map.of("id", id);
        ApiClient.delete(url, requestBody);
    }

    public static Discount getDiscountById(int id) throws IOException {
        String url = AppConfig.API_URL_BASE + "discount?id=" + id;
        String apiResponse = ApiClient.get(url);
        return new Gson().fromJson(apiResponse, Discount.class);
    }

    public static List<Discount> getAllDiscounts() throws IOException {
        String url = AppConfig.API_URL_BASE + "discounts";
        String apiResponse = ApiClient.get(url);
        Type discountListType = new TypeToken<List<Discount>>() {}.getType();
        return new Gson().fromJson(apiResponse, discountListType);
    }
}