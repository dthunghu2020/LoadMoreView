package com.example.apiseemore.api;

import com.example.apiseemore.model.comment.AllCommentResponse;
import com.example.apiseemore.model.commentDetail.AllCommentDetailResponse;
import com.example.apiseemore.model.login.AllLoginResponse;
import com.example.apiseemore.model.login.LoginPostRequest;
import com.example.apiseemore.model.peopleLiked.AllPeopleLikedResponse;
import com.example.apiseemore.model.seemore.AllSeeMoreResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("auth/login")
    Call<AllLoginResponse> postLogin(@Body LoginPostRequest loginPostRequest);

    @GET("diary/get_by_season/127")
    Call<AllSeeMoreResponse> getSeemore(@Header("Authorization") String token, @Query("pageLimit") String pageLimit, @Query("page") String page);

    @GET("comment/getList/5/{refer_id}")
    Call<AllCommentResponse> getComment(@Path("refer_id") String refer_id, @Query("pageLimit") String pageLimit, @Query("page") String page, @Query("token") String token);

    @GET("comment/getListByParent/{parent_id}")
    Call<AllCommentDetailResponse> getCommentDetail(@Path("parent_id") String parent_id, @Query("token") String token);

//    @POST("like/liked")
//    Call<DataLiked> postDataLiked(@Header("Authorization") String token, @Body DataPostLiked dataPostLiked);
//
//    @POST("like/unLiked")
//    Call<DataLiked> postDataUnLiked(@Header("Authorization") String token, @Body DataPostLiked dataPostLiked);

    @GET("like/listLiked/5/{referId}")
    Call<AllPeopleLikedResponse> getDataPeopleLiked(@Path("referId") String referId, @Query("token") String token);
}
