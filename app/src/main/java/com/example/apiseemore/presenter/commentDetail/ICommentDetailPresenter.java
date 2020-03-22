package com.example.apiseemore.presenter.commentDetail;

import com.example.apiseemore.model.commentDetail.Datum;

import java.util.List;

public interface ICommentDetailPresenter {
    void onGetCDSuccess(List<Datum> datas);
    void onGetCDFail();
}
