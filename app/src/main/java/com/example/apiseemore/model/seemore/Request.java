package com.example.apiseemore.model.seemore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("pageLimit")
    @Expose
    private String pageLimit;
    @SerializedName("page")
    @Expose
    private String page;

    public String getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(String pageLimit) {
        this.pageLimit = pageLimit;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}