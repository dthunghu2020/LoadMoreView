package com.example.apiseemore.presenter.peopleLiked;

import androidx.annotation.NonNull;

import com.example.apiseemore.api.ApiService;
import com.example.apiseemore.api.ApiUtils;
import com.example.apiseemore.model.peopleLiked.AllPeopleLikedResponse;
import com.example.apiseemore.model.peopleLiked.Datum;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleLikedPresenter {
    private IPeopleLikedPresenter iPeopleLikedPresenter;
    private int REQUEST_CODE = 200;

    public PeopleLikedPresenter(IPeopleLikedPresenter iPeopleLikedPresenter) {
        this.iPeopleLikedPresenter = iPeopleLikedPresenter;
    }


    public void getListPL(String commentID, String token) {
        ApiService apiService = ApiUtils.postService();

        apiService.getDataPeopleLiked(commentID, "Bearer" + token).enqueue(new Callback<AllPeopleLikedResponse>() {
            @Override
            public void onResponse(@NonNull Call<AllPeopleLikedResponse> call, @NonNull Response<AllPeopleLikedResponse> response) {
                if (response.code() == REQUEST_CODE) {
                    assert response.body() != null;
                    iPeopleLikedPresenter.onGetPLSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllPeopleLikedResponse> call, @NonNull Throwable t) {
                iPeopleLikedPresenter.onGetPLFail();
            }
        });
    }
}
