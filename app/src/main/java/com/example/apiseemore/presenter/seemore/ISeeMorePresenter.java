package com.example.apiseemore.presenter.seemore;

import com.example.apiseemore.model.seemore.Datum;

import java.util.List;

public interface ISeeMorePresenter {
    void onGetDataSuccess(List<Datum> dataList,int recordsTotal);
    void onGetDataFail();
}