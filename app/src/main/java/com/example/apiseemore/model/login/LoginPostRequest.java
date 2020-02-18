package com.example.apiseemore.model.login;

public class LoginPostRequest {
    private String username;
    private String password;
    private String tenantcode;
    private String language;

    public LoginPostRequest(String username, String password, String tenantcode, String language) {
        this.username = username;
        this.password = password;
        this.tenantcode = tenantcode;
        this.language = language;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTenantcode() {
        return tenantcode;
    }

    public void setTenantcode(String tenantcode) {
        this.tenantcode = tenantcode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
