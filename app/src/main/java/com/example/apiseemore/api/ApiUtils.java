package com.example.apiseemore.api;

public class ApiUtils {
    private static final String BASE_URL = "https://api.nextfarm.vn/api/";

    public static  ApiService postLoginService(){
        return RetrofitClient.getRetrofit(BASE_URL).create(ApiService.class);
    }

}
