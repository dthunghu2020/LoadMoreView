package com.example.apiseemore.presenter.login;

public interface ILoginPresenter {
    void onLoginSuccess(String token);
    void onLoginFail();
}
