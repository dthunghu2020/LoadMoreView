package com.example.apiseemore.presenter.login;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.apiseemore.api.ApiService;
import com.example.apiseemore.api.ApiUtils;
import com.example.apiseemore.model.login.AllLoginResponse;
import com.example.apiseemore.model.login.LoginPostRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private static final int REQUEST_CODE = 200;
    private ILoginPresenter mILoginprisenter;

    public LoginPresenter(ILoginPresenter mILoginprisenter) {
        this.mILoginprisenter = mILoginprisenter;
    }

    public void checkEdtUserLogin(String edtUserId, String edtUseName, String edtUserPass) {
        if (edtUserId.isEmpty()) {
            mILoginprisenter.onLoginFail();
        } else if (edtUseName.isEmpty()) {
            mILoginprisenter.onLoginFail();
        } else if (edtUserPass.isEmpty()) {
            mILoginprisenter.onLoginFail();
        } else
            checkLoginRequestAPI(edtUseName, edtUserPass, edtUserId);
    }

    private void checkLoginRequestAPI(String useName, String userPass, String userId) {
        ApiService mApiService = ApiUtils.postLoginService();

        LoginPostRequest loginPostRequest = new LoginPostRequest(useName, userPass, userId, "");
        Call<AllLoginResponse> call = mApiService.postLogin(loginPostRequest);
        call.enqueue(new Callback<AllLoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<AllLoginResponse> call, @NonNull Response<AllLoginResponse> response) {
                if (response.code() == REQUEST_CODE) {
                    assert response.body() != null;
                    mILoginprisenter.onLoginSuccess(response.body().getData().getToken());
                    Log.e("HDT0309",response.body().getData().getToken());
                } else {
                    mILoginprisenter.onLoginFail();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllLoginResponse> call, @NonNull Throwable t) {
                mILoginprisenter.onLoginFail();
            }
        });

    }
}