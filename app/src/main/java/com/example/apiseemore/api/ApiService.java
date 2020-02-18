package com.example.apiseemore.api;

import com.example.apiseemore.model.login.AllLoginResponse;
import com.example.apiseemore.model.login.LoginPostRequest;
import com.example.apiseemore.model.seemore.AllSeeMoreResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("auth/login")
    Call<AllLoginResponse> postLogin(@Body LoginPostRequest loginPostRequest);

    @GET("diary/get_by_season/127")
    Call<AllSeeMoreResponse> getSeemore(@Header("Authorization") String token, @Query("pageLimit") String pageLimit, @Query("page") String page);
}
