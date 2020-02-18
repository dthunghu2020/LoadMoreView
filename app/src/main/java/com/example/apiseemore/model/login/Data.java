package com.example.apiseemore.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("sys_message")
    @Expose
    private Object sysMessage;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getSysMessage() {
        return sysMessage;
    }

    public void setSysMessage(Object sysMessage) {
        this.sysMessage = sysMessage;
    }

}