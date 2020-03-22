package com.example.apiseemore.presenter.peopleLiked;

import com.example.apiseemore.model.peopleLiked.Datum;

import java.util.List;

public interface IPeopleLikedPresenter {
    void onGetPLSuccess(List<Datum> listPeopleLiked);
    void onGetPLFail();
}
