package com.example.apiseemore.presenter.seemore;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.apiseemore.api.ApiService;
import com.example.apiseemore.api.ApiUtils;
import com.example.apiseemore.model.seemore.AllSeeMoreResponse;
import com.example.apiseemore.model.seemore.Datum;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeMorePresenter {
    private static final int REQUEST_CODE = 200;
    private ISeeMorePresenter mISeeMorePresenter;

    public SeeMorePresenter(ISeeMorePresenter mISeemorePresenter) {
        this.mISeeMorePresenter = mISeemorePresenter;
    }

    public void getDataSM(String token,String pageLimit,String page) {
        ApiService mApiService = ApiUtils.postLoginService();

        mApiService.getSeemore(token, pageLimit, page).enqueue(new Callback<AllSeeMoreResponse>() {
            @Override
            public void onResponse(@NonNull Call<AllSeeMoreResponse> call, @NonNull Response<AllSeeMoreResponse> response) {
                if (response.code() == REQUEST_CODE) {
                    assert response.body() != null;
                    List<Datum> dataList = response.body().getData();
                    mISeeMorePresenter.onGetDataSuccess(dataList,response.body().getRecordsTotal());
                } else {
                    mISeeMorePresenter.onGetDataFail();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllSeeMoreResponse> call, @NonNull Throwable t) {
                mISeeMorePresenter.onGetDataFail();
            }
        });
    }
}
