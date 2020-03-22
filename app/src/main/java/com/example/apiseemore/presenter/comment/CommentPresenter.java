package com.example.apiseemore.presenter.comment;

import androidx.annotation.NonNull;

import com.example.apiseemore.api.ApiService;
import com.example.apiseemore.api.ApiUtils;
import com.example.apiseemore.model.comment.AllCommentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentPresenter {

    private ICommentPresenter iCommentPresenter;
    private int REQUEST_CODE = 200;

    public CommentPresenter(ICommentPresenter iCommentPresenter) {
        this.iCommentPresenter = iCommentPresenter;
    }

    public void getCommentData(String commentID, String pageLimit, String page, String token) {
        ApiService apiService = ApiUtils.postService();

        apiService.getComment(commentID, pageLimit, page, token).enqueue(new Callback<AllCommentResponse>() {
            @Override
            public void onResponse(@NonNull Call<AllCommentResponse> call,@NonNull Response<AllCommentResponse> response) {
                if (response.code() == REQUEST_CODE) {
                    AllCommentResponse allCommentResponse = response.body();
                    assert allCommentResponse != null;
                    iCommentPresenter.onRequestCommentSuccess(allCommentResponse.getData(),allCommentResponse.getRecordsTotal());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllCommentResponse> call,@NonNull Throwable t) {
                iCommentPresenter.onRequestCommentFail();
            }
        });
    }
}
