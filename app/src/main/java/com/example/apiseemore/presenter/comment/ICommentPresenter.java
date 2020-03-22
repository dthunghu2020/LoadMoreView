package com.example.apiseemore.presenter.comment;

import com.example.apiseemore.model.comment.AllCommentResponse;
import com.example.apiseemore.model.comment.Datum;

import java.util.List;

public interface ICommentPresenter {
    void onRequestCommentSuccess(List<Datum> commentList,int recordsTotal);
    void onRequestCommentFail();
}
