package com.example.apiseemore.presenter.commentDetail;

import androidx.annotation.NonNull;

import com.example.apiseemore.api.ApiService;
import com.example.apiseemore.api.ApiUtils;
import com.example.apiseemore.model.commentDetail.AllCommentDetailResponse;
import com.example.apiseemore.model.commentDetail.Datum;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentDetailPresenter {
    private ICommentDetailPresenter iCDP;
    private int REQUEST_CODE = 200;

    public CommentDetailPresenter(ICommentDetailPresenter iCDP) {
        this.iCDP = iCDP;
    }

    public void getSubData(int commentDetailID, String token) {
        ApiService apiService = ApiUtils.postService();

        apiService.getCommentDetail(String.valueOf(commentDetailID),token).enqueue(new Callback<AllCommentDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<AllCommentDetailResponse> call,@NonNull Response<AllCommentDetailResponse> response) {
                if(response.code()==REQUEST_CODE){
                    assert response.body() != null;
                    List<Datum> datas = response.body().getData();
                    iCDP.onGetCDSuccess(datas);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllCommentDetailResponse> call,@NonNull Throwable t) {
                iCDP.onGetCDFail();
            }
        });
    }
}
